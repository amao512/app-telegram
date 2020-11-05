package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.aslnstbk.telegram.R

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

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
            R.id.settings_action_menu_edit -> Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show()
            R.id.settings_action_menu_exit -> Toast.makeText(activity, "Exit", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}