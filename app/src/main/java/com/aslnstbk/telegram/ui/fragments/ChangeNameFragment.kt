package com.aslnstbk.telegram.ui.fragments

import android.widget.EditText
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.utils.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    private lateinit var nameEditText: EditText

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        nameEditText = APP_ACTIVITY.findViewById(R.id.fragment_settings_change_name)

        if(USER.fullname.isNotEmpty()){
            nameEditText.setText(USER.fullname)
        }
    }

    override fun change() {
        super.change()

        val fullName = nameEditText.text.toString()

        if (fullName.isEmpty()) {
            showToast("Name is empty!")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERS)
                    .child(CURRENT_UID)
                    .child(CHILD_FULLNAME)
                    .setValue(fullName)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Success")
                            USER.fullname = fullName
                            fragmentManager?.popBackStack()
                        } else {
                            showToast(it.exception?.message.toString())
                        }
                    }
        }
    }
}