package com.aslnstbk.telegram.ui.fragments

import android.widget.EditText
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.utils.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    private lateinit var usernameEditText: EditText

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        usernameEditText = APP_ACTIVITY.findViewById(R.id.fragment_change_username_edit_text)
        usernameEditText.setText(USER.username)
    }

    override fun change() {
        super.change()

        val username = usernameEditText.text.toString()

        if(username.isEmpty()){
            showToast("Input is empty!")
        } else {
            checkUsername(username)
        }
    }

    private fun checkUsername(username: String) {
        REF_DATABASE_ROOT.child(NODE_USERNAMES)
            .addListenerForSingleValueEvent(AppValueEventListener {
                    if(it.hasChild(username) && username != USER.username){
                        showToast("Username is already exists!")
                    } else if (username == USER.username) {
                        fragmentManager?.popBackStack()
                    } else {
                        setUsername(username)
                    }
            })
    }

    private fun setUsername(username: String) {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(username).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    updateCurrentUsername(username)
                } else {
                    showToast(it.exception?.message.toString())
                }
            }

    }

    private fun updateCurrentUsername(username: String) {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME).setValue(username)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    deleteOldUsername(username)
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUsername(username: String) {

        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue().addOnCompleteListener {
            if(it.isSuccessful){
                USER.username = username
                fragmentManager?.popBackStack()
            } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}