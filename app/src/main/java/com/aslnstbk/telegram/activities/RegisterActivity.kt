package com.aslnstbk.telegram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.databinding.ActivityRegisterBinding
import com.aslnstbk.telegram.replaceFragment
import com.aslnstbk.telegram.ui.fragments.RegisterEnterPhoneFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields(){
        mToolbar = mBinding.registerToolbar
        title = getString(R.string.register_toolbar_title)

        replaceFragment(RegisterEnterPhoneFragment())
    }

    private fun initFunc(){
        setSupportActionBar(mToolbar)
    }
}