package com.nicolasmorais.todolistapp.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nicolasmorais.todolistapp.data.datasource.LoginDataSource
import com.nicolasmorais.todolistapp.data.model.UserModel
import com.nicolasmorais.todolistapp.data.model.toUserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepository(
     val firebaseAuth: FirebaseAuth,

    ) {
    private val loginDataSource = LoginDataSource(firebaseAuth)

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener {
            _currentUser.value = it.currentUser
        }
    }


    suspend fun login(email: String, password: String): UserModel {
        val user = loginDataSource.login(email, password)
        return user.toUserModel()
    }

    suspend fun registerUser(email: String, password: String): UserModel {
        val user = loginDataSource.registerUser(email, password)
        return user.toUserModel()
    }

    fun signOut() {
        loginDataSource.signOut()

    }
}