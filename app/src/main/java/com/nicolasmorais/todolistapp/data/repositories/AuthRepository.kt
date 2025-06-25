package com.nicolasmorais.todolistapp.data.repositories

import com.nicolasmorais.todolistapp.data.datasource.LoginDataSource
import com.nicolasmorais.todolistapp.data.model.UserModel
import com.nicolasmorais.todolistapp.data.model.toUserModel

class AuthRepository {
    private val loginDataSource = LoginDataSource()

    suspend fun login(email: String, password: String): UserModel {
        val user = loginDataSource.login(email, password)
        return user.toUserModel()
    }

    suspend fun registerUser(email: String, password: String): UserModel {
        val user = loginDataSource.registerUser(email, password)
        return user.toUserModel()
    }
}