package com.nicolasmorais.todolistapp.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class LoginDataSource(
    private val firebaseAuth: FirebaseAuth,
) {
    suspend fun login(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Usuário não encontrado")
    }

    suspend fun registerUser(email: String, password: String): FirebaseUser {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user ?: throw Exception("Usuário retornado é nulo")
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

}