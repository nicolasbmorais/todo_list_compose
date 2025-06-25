package com.nicolasmorais.todolistapp.data.model

import com.google.firebase.auth.FirebaseUser

data class UserModel(
    val uid: String,
    val email: String? = null,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val isEmailVerified: Boolean = false,
    val phoneNumber: String? = null
)

fun FirebaseUser.toUserModel(): UserModel {
    return UserModel(
        uid = uid,
        email = email,
        displayName = displayName,
        photoUrl = photoUrl?.toString(),
        isEmailVerified = isEmailVerified,
        phoneNumber = phoneNumber
    )
}