package com.aslnstbk.telegram.ui.fragments

import android.widget.EditText
import android.widget.Toast
import com.aslnstbk.telegram.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterEnterPhoneFragment : BaseFragment(R.layout.fragment_register_enter_phone) {

    private var phoneEditText: EditText? = null
    private var nextBtn: FloatingActionButton? = null

    override fun onResume() {
        super.onResume()
        initFields()

        nextBtn?.setOnClickListener {
            if(phoneEditText?.text.toString().isEmpty()){
                Toast.makeText(activity?.applicationContext, getString(R.string.register_phone_number_text), Toast.LENGTH_SHORT).show()
            } else {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.registerContainer, RegisterEnterCodeFragment())
                    ?.commit()
            }
        }
    }

    private fun initFields(){
        phoneEditText = activity?.findViewById(R.id.register_enter_phone_edit_text)
        nextBtn = activity?.findViewById(R.id.register_enter_phone_btn_next)
    }
}