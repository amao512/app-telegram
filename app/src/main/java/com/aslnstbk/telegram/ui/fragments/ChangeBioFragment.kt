package com.aslnstbk.telegram.ui.fragments

import android.widget.EditText
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.utils.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    private var bioEditText: EditText? = null

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        bioEditText = activity?.findViewById(R.id.fragment_settings_change_bio_edit_text)

        if(USER.bio.isNotEmpty()){
            bioEditText?.setText(USER.bio)
        }
    }

    override fun change() {
        super.change()

        val bio = bioEditText?.text.toString()

        if (bio.isEmpty()) {
            showToast("Name is empty!")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERS)
                    .child(UID)
                    .child(CHILD_BIO)
                    .setValue(bio)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Success")
                            USER.bio = bio
                            fragmentManager?.popBackStack()
                        } else {
                            showToast(it.exception?.message.toString())
                        }
                    }
        }
    }
}