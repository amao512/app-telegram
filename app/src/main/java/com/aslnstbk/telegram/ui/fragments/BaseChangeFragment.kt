package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.utils.hideKeyboard

open class BaseChangeFragment(layout: Int) : BaseFragment(layout) {
    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        hideKeyboard()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_change_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_change_confirm_menu_item -> change()
        }

        return super.onOptionsItemSelected(item)
    }

    open fun change() {}
}