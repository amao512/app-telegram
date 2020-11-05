package com.aslnstbk.telegram

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aslnstbk.telegram.activities.RegisterActivity
import com.aslnstbk.telegram.databinding.ActivityMainBinding
import com.aslnstbk.telegram.ui.fragments.ChatsFragment
import com.aslnstbk.telegram.ui.objects.AppDrawer

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
    }

    private fun initFunc() {
        if(false){
            setSupportActionBar(mToolbar)

            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatsFragment())
                .commit()

            mAppDrawer.create()
        } else {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}