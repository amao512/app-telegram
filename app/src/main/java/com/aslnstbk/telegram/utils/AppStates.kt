package com.aslnstbk.telegram.utils

enum class AppStates(val state: String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("печатает...");

    companion object {
        fun update(appStates: AppStates){
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                .setValue(appStates.state)
                .addOnSuccessListener {
                    USER.state = appStates.state
                }
                .addOnFailureListener {
                    showToast(it.localizedMessage.toString())
                }
        }
    }
}