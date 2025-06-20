package com.nicolasmorais.todolistapp.ui.view.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {

    var email = MutableStateFlow("")
    var password = MutableStateFlow("")


    fun setEmail(value: String) {
        email.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun setConfirmPassword(value: String) {
        password.value = value
    }

}