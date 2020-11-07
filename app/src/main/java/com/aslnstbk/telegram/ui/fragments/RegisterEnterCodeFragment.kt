package com.aslnstbk.telegram.ui.fragments

import android.content.Intent
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.aslnstbk.telegram.MainActivity
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.replaceActivity
import com.aslnstbk.telegram.ui.objects.AbstractTextWatcher

class RegisterEnterCodeFragment : BaseFragment(R.layout.fragment_register_enter_code) {

    private var inputCode: EditText? = null

    override fun onResume() {
        super.onResume()
        initFields()

        inputCode?.addTextChangedListener( object : AbstractTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if(s?.toString()?.length == 6){
                    Toast.makeText(activity?.applicationContext, "OK", Toast.LENGTH_SHORT).show()
                    replaceActivity(MainActivity())
                }
            }
        })
    }

    private fun initFields(){
        inputCode = activity?.findViewById(R.id.register_enter_code_input_code)
    }
}