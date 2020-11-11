package com.aslnstbk.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.aslnstbk.telegram.MainActivity
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.utils.*

class SettingsChangeFragment : BaseFragment(R.layout.fragment_settings_change) {

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText

    override fun onResume() {
        super.onResume()
        initViews()

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.settings_change_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings_change_confirm_menu_item -> changeUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViews(){
        nameEditText = (activity as MainActivity).findViewById(R.id.fragment_settings_change_name)
        surnameEditText = (activity as MainActivity).findViewById(R.id.fragment_settings_change_surname)

        val fullName = USER.fullname.split(" ")
        nameEditText.setText(fullName[0] ?: "")
        surnameEditText.setText(fullName[1] ?: "")
    }

    private fun changeUser(){
        val name = nameEditText.text.toString()
        val surname = surnameEditText.text.toString()
        val fullName = "$name $surname"

        if(name.isEmpty()){
            Toast.makeText(activity?.applicationContext, "Name is empty!", Toast.LENGTH_SHORT).show()
        } else {
            REF_DATABASE_ROOT.child(NODE_USERS)
                .child(UID)
                .child(FULLNAME)
                .setValue(fullName)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity?.applicationContext, "Success", Toast.LENGTH_SHORT).show()
                        USER.fullname = fullName
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}