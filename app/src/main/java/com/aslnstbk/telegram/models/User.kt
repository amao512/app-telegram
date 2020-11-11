package com.aslnstbk.telegram.models

data class User(
    val id: String = "",
    var phone: String = "",
    var username: String = "",
    var fullname: String = "",
    var bio: String = "",
    var photoUrl: String = ""
)