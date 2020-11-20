package com.aslnstbk.telegram.models

data class CommonModel (
    val id: String = "",
    var phone: String = "",
    var username: String = "",
    var fullname: String = "",
    var bio: String = "",
    var photoUrl: String = "empty",
    var state: String = ""
)