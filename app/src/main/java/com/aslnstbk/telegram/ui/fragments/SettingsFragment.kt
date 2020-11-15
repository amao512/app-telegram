package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.utils.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private lateinit var fullNameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var changeProfilePhoto: ImageView

    private lateinit var usernameItem: ConstraintLayout
    private lateinit var bioItem: ConstraintLayout

    override fun onStart() {
        super.onStart()
        setSettingsItemViews()
        initFirebase()
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.settings_action_menu_edit -> APP_ACTIVITY.replaceFragment(ChangeNameFragment())
            R.id.settings_action_menu_exit -> {
                AUTH.signOut()
                APP_ACTIVITY.replaceActivity(RegisterActivity())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setSettingsItemViews(){
        initViews(R.id.settings_item_notify, R.drawable.ic_notify, R.string.settings_notify_text)
        initViews(R.id.settings_item_data, R.drawable.ic_data_usage, R.string.settings_data_text)
        initViews(R.id.settings_item_privacy, R.drawable.ic_privacy, R.string.settings_privacy_text)
        initViews(R.id.settings_item_themes, R.drawable.ic_theme, R.string.settings_themes_text)
    }

    private fun initViews(layoutId: Int, iconRes: Int, title: Int){
        fullNameTextView = APP_ACTIVITY.findViewById(R.id.settings_fullname)
        usernameTextView = APP_ACTIVITY.findViewById(R.id.settings_user_login_text)
        phoneNumberTextView = APP_ACTIVITY.findViewById(R.id.settings_user_phone_text)
        bioTextView = APP_ACTIVITY.findViewById(R.id.settings_user_bio_text)
        changeProfilePhoto = APP_ACTIVITY.findViewById(R.id.settings_profile_image_change_btn)

        usernameItem = APP_ACTIVITY.findViewById(R.id.settings_item_username)
        bioItem = APP_ACTIVITY.findViewById(R.id.settings_item_user_bio)

        fillFields()

        val item: ConstraintLayout? = activity?.findViewById(layoutId)
        val icon: ImageView? = item?.findViewById(R.id.settings_item_icon)
        val text: TextView? = item?.findViewById(R.id.settings_item_text)

        icon?.setImageResource(iconRes)
        text?.text = getString(title)

        usernameItem.setOnClickListener {
            replaceFragment(ChangeUsernameFragment())
        }

        bioItem.setOnClickListener {
            replaceFragment(ChangeBioFragment())
        }

        changeProfilePhoto.setOnClickListener {
            changeUserPhoto()
        }
    }

    private fun fillFields(){
        when {
            USER.fullname.isNotEmpty() -> fullNameTextView.text = USER.fullname
            USER.username.isNotEmpty() -> usernameTextView.text = USER.username
            USER.phone.isNotEmpty() -> phoneNumberTextView.text = USER.phone
            USER.bio.isNotEmpty() -> bioTextView.text = USER.bio
        }
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY)
    }
}