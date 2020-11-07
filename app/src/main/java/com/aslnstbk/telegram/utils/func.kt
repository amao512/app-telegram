package com.aslnstbk.telegram.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aslnstbk.telegram.R

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity){
    startActivity(Intent(this, activity::class.java))
    finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, fragment)
            .commit()
}

fun Fragment.replaceActivity(activity: AppCompatActivity){
    startActivity(Intent(this.context, activity::class.java))
    activity.finish()
}

fun Fragment.replaceFragment(fragment: Fragment){
    activity?.supportFragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.dataContainer, fragment)
            ?.commit()
}