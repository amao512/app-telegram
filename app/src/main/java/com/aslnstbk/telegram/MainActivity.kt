package com.aslnstbk.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.databinding.ActivityMainBinding
import com.aslnstbk.telegram.ui.fragments.ChatsFragment
import com.aslnstbk.telegram.ui.objects.AppDrawer
import com.aslnstbk.telegram.utils.AUTH
import com.aslnstbk.telegram.utils.initFirebase
import com.aslnstbk.telegram.utils.replaceActivity
import com.aslnstbk.telegram.utils.replaceFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()
    }

    private fun initFunc() {
        if(AUTH.currentUser != null){
            setSupportActionBar(mToolbar)
            replaceFragment(ChatsFragment())
            mAppDrawer.create()
        } else {
            replaceActivity(RegisterActivity())
        }
    }
}