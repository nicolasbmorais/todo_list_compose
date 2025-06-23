package com.nicolasmorais.todolistapp.ui.view.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {

    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var confirmPassword = MutableStateFlow("")

    var isValidPassword = MutableStateFlow(false)

    fun setEmail(value: String) {
        email.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun setConfirmPassword(value: String) {
        confirmPassword.value = value
        if (password.value == confirmPassword.value) {
            isValidPassword.value = true
        }
    }


    fun registerUser() {
        if (password.value == confirmPassword.value) {

        }
    }
}