package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.aslnstbk.telegram.R

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onStart() {
        super.onStart()
        setSettingsItemViews()
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
            R.id.settings_action_menu_edit -> Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show()
            R.id.settings_action_menu_exit -> Toast.makeText(activity, "Exit", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}