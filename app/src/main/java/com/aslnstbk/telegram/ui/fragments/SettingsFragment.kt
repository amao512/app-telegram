package com.aslnstbk.telegram.ui.fragments

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.utils.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import de.hdodenhof.circleimageview.CircleImageView

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private lateinit var fullNameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var changeProfilePhoto: ImageView
    private lateinit var profileImage: CircleImageView
    private lateinit var userStatusTextView: TextView

    private lateinit var usernameItem: ConstraintLayout
    private lateinit var bioItem: ConstraintLayout

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initView()
        setSettingsItemViews()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == AppCompatActivity.RESULT_OK && data != null){

            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(CURRENT_UID)

            putImageToStorage(uri, path){
                getUrlFromStorage(path){
                    putUrlToDatabase(it){
                        profileImage.downloadPhoto(it)
                        showToast("Photo uploaded")
                        USER.photoUrl = it

                        APP_ACTIVITY.mAppDrawer.updateProfile()
                    }
                }
            }
        }
    }

    private fun initView() {
        fullNameTextView = APP_ACTIVITY.findViewById(R.id.settings_fullname)
        usernameTextView = APP_ACTIVITY.findViewById(R.id.settings_user_login_text)
        phoneNumberTextView = APP_ACTIVITY.findViewById(R.id.settings_user_phone_text)
        bioTextView = APP_ACTIVITY.findViewById(R.id.settings_user_bio_text)
        changeProfilePhoto = APP_ACTIVITY.findViewById(R.id.settings_profile_image_change_btn)
        profileImage = APP_ACTIVITY.findViewById(R.id.settings_profile_image)
        userStatusTextView = APP_ACTIVITY.findViewById(R.id.settings_user_online_status)

        usernameItem = APP_ACTIVITY.findViewById(R.id.settings_item_username)
        bioItem = APP_ACTIVITY.findViewById(R.id.settings_item_user_bio)

        initFields()

        usernameItem.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        bioItem.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        changeProfilePhoto.setOnClickListener { changeUserPhoto() }
    }

    private fun initFields(){
        fullNameTextView.text = USER.fullname
        usernameTextView.text = USER.username
        phoneNumberTextView.text = USER.phone
        bioTextView.text = USER.bio
        profileImage.downloadPhoto(USER.photoUrl)
        userStatusTextView.text = USER.state
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY, this)
    }

    private fun setSettingsItemViews(){
        initViews(R.id.settings_item_notify, R.drawable.ic_notify, R.string.settings_notify_text)
        initViews(R.id.settings_item_data, R.drawable.ic_data_usage, R.string.settings_data_text)
        initViews(R.id.settings_item_privacy, R.drawable.ic_privacy, R.string.settings_privacy_text)
        initViews(R.id.settings_item_themes, R.drawable.ic_theme, R.string.settings_themes_text)
    }

    private fun initViews(layoutId: Int, iconRes: Int, title: Int){
        val item: ConstraintLayout? = APP_ACTIVITY.findViewById(layoutId)
        val icon: ImageView? = item?.findViewById(R.id.settings_item_icon)
        val text: TextView? = item?.findViewById(R.id.settings_item_text)

        icon?.setImageResource(iconRes)
        text?.text = getString(title)
    }
}