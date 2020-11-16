package com.aslnstbk.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.databinding.ActivityMainBinding
import com.aslnstbk.telegram.models.User
import com.aslnstbk.telegram.ui.fragments.ChatsFragment
import com.aslnstbk.telegram.ui.objects.AppDrawer
import com.aslnstbk.telegram.utils.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        APP_ACTIVITY = this
        initFirebase()
        initUser()
        initFields()
        initFunc()
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
                .addListenerForSingleValueEvent(AppValueEventListener {
                        USER = it.getValue(User::class.java) ?: User()
                })
    }

    private fun initFunc() {
        if(AUTH.currentUser != null){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())
        } else {
            replaceActivity(RegisterActivity())
        }
    }
}