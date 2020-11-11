package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.aslnstbk.telegram.MainActivity
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.utils.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private lateinit var fullNameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var bioTextView: TextView

    override fun onStart() {
        super.onStart()
        setSettingsItemViews()
        initFirebase()
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    private fun setSettingsItemViews(){
        initViews(R.id.settings_item_notify, R.drawable.ic_notify, R.string.settings_notify_text)
        initViews(R.id.settings_item_data, R.drawable.ic_data_usage, R.string.settings_data_text)
        initViews(R.id.settings_item_privacy, R.drawable.ic_privacy, R.string.settings_privacy_text)
        initViews(R.id.settings_item_themes, R.drawable.ic_theme, R.string.settings_themes_text)
    }

    private fun initViews(layoutId: Int, iconRes: Int, title: Int){
        fullNameTextView = (activity as MainActivity).findViewById(R.id.settings_fullname)
        usernameTextView = (activity as MainActivity).findViewById(R.id.settings_user_login_text)
        phoneNumberTextView = (activity as MainActivity).findViewById(R.id.settings_user_phone_text)
        bioTextView = (activity as MainActivity).findViewById(R.id.settings_user_bio_text)

        fullNameTextView.text = USER.fullname
        usernameTextView.text = USER.username
        phoneNumberTextView.text = USER.phone
        bioTextView.text = USER.bio

        val item: ConstraintLayout? = activity?.findViewById(layoutId)
        val icon: ImageView? = item?.findViewById(R.id.settings_item_icon)
        val text: TextView? = item?.findViewById(R.id.settings_item_text)

        icon?.setImageResource(iconRes)
        text?.text = getString(title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.settings_action_menu_edit -> (activity as MainActivity).replaceFragment(SettingsChangeFragment())
            R.id.settings_action_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }

        return super.onOptionsItemSelected(item)
    }
}