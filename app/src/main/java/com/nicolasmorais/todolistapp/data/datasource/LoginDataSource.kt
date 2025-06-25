package com.nicolasmorais.todolistapp.data.datasource

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class LoginDataSource {
    private var auth = Firebase.auth
    val TAG = "LoginDataSource"

    suspend fun login(email: String, password: String): FirebaseUser {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Usuário não encontrado")
    }

    suspend fun registerUser(email: String, password: String): FirebaseUser {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Usuário retornado é nulo")
    }

}