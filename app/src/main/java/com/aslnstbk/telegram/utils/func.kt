package com.aslnstbk.telegram.utils

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aslnstbk.telegram.R
import com.squareup.picasso.Picasso

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity){
    startActivity(Intent(this, activity::class.java))
    finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction()
            .addToBackStack(null)
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

fun showToast(text: String){
    Toast.makeText(APP_ACTIVITY, text, Toast.LENGTH_SHORT).show()
}

fun hideKeyboard(){
    val imm: InputMethodManager = APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}

fun ImageView.downloadPhoto(url: String){
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.ic_default_user_photo)
        .into(this)
}