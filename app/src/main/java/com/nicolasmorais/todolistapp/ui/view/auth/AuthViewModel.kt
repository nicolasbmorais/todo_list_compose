package com.nicolasmorais.todolistapp.ui.view.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolasmorais.todolistapp.data.repositories.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _userState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val userState: StateFlow<AuthUiState> = _userState

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


    fun login() {
        if (isEmailValid(email.value) && password.value.isNotEmpty()) {
            viewModelScope.launch {
                _userState.value = AuthUiState.Loading
                try {
                    val user = repository.login(email.value, confirmPassword.value)
                    _userState.value = AuthUiState.Success(user)
                } catch (e: Exception) {
                    _userState.value = AuthUiState.Error(e.message ?: "Erro ao fazer login")
                }
            }
        }
    }

    fun registerUser(context: Context) {
        if (isEmailValid(email.value) && password.value == confirmPassword.value) {
            viewModelScope.launch {
                _userState.value = AuthUiState.Loading
                try {
                    val user = repository.registerUser(email.value, confirmPassword.value)
                    _userState.value = AuthUiState.Success(user)
                } catch (e: Exception) {
                    _userState.value = AuthUiState.Error(e.message ?: "Erro desconhecido")
                }
            }
        } else {
            Toast.makeText(context, registerErrorMsg(email.value), Toast.LENGTH_LONG).show()
        }
    }


    private fun registerErrorMsg(email: String): String {
        if (!isEmailValid(email)) {
            return "Digite um email válido"

        } else if (password.value != confirmPassword.value) {
            return "As senhas devem ser iguais"
        } else if (password.value.isEmpty() || confirmPassword.value.isEmpty()) {

            return "Senha não pode ser vazia"
        }
        return ""
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }

}