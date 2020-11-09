package com.aslnstbk.telegram.ui.fragments

import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.aslnstbk.telegram.MainActivity
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.ui.objects.AbstractTextWatcher
import com.aslnstbk.telegram.utils.*
import com.google.firebase.auth.PhoneAuthProvider

class RegisterEnterCodeFragment(val phoneNumber: String, val id: String) : BaseFragment(R.layout.fragment_register_enter_code) {

    private var inputCode: EditText? = null

    override fun onResume() {
        super.onResume()
        initFields()

        inputCode?.addTextChangedListener( object : AbstractTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if(s?.toString()?.length == 6){
                    enterCode()
                }
            }
        })
    }

    private fun initFields(){
        inputCode = activity?.findViewById(R.id.register_enter_code_input_code)
    }

    private fun enterCode(){
        val code = inputCode?.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val uid = AUTH.currentUser?.uid.toString()

                val database = mutableMapOf<String, Any>()
                database[CHILD_ID] = uid
                database[CHILD_PHONE] = phoneNumber
                database[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS)
                    .child(uid)
                    .updateChildren(database)
                    .addOnCompleteListener {task ->

                    if(task.isSuccessful){
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            task.exception?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    activity?.applicationContext,
                    it.exception?.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}