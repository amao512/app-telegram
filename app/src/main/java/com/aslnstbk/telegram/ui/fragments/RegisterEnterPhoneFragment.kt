package com.aslnstbk.telegram.ui.fragments

import android.widget.EditText
import android.widget.Toast
import com.aslnstbk.telegram.MainActivity
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.utils.AUTH
import com.aslnstbk.telegram.utils.replaceActivity
import com.aslnstbk.telegram.utils.replaceFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class RegisterEnterPhoneFragment : BaseFragment(R.layout.fragment_register_enter_phone) {

    private var phoneEditText: EditText? = null
    private var nextBtn: FloatingActionButton? = null

    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private fun initFields(){
        phoneEditText = activity?.findViewById(R.id.register_enter_phone_edit_text)
        nextBtn = activity?.findViewById(R.id.register_enter_phone_btn_next)
    }

    override fun onStart() {
        super.onStart()
        initFields()

        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            it.exception?.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(
                    activity?.applicationContext,
                    p0.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, p1)
                replaceFragment(RegisterEnterCodeFragment(mPhoneNumber, id))
            }
        }

        nextBtn?.setOnClickListener { sendCode() }
    }

    private fun sendCode(){
        if(phoneEditText?.text.toString().isEmpty()){
            Toast.makeText(activity?.applicationContext, getString(R.string.register_phone_number_text), Toast.LENGTH_SHORT).show()
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = phoneEditText?.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )
    }

}