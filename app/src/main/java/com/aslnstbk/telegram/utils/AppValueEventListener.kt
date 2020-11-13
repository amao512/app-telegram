package com.aslnstbk.telegram.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

open class AppValueEventListener(val onSuccess: (snapshot: DataSnapshot) -> Unit) : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot){
        onSuccess(snapshot)
    }

    override fun onCancelled(error: DatabaseError){}
}