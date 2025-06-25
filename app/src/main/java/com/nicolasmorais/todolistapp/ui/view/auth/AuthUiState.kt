package com.nicolasmorais.todolistapp.ui.view.auth

import com.nicolasmorais.todolistapp.data.model.UserModel

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: UserModel) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
