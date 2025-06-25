package com.nicolasmorais.todolistapp.ui.view.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.ui.components.CustomTextFieldComponent
import com.nicolasmorais.todolistapp.ui.theme.Purple40
import com.nicolasmorais.todolistapp.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUser(
    navController: NavController,
    viewModel: AuthViewModel = viewModel(),
) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val isValidPassword by viewModel.isValidPassword.collectAsState()
    val state by viewModel.userState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Cadastrar usuário")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextFieldComponent(
                modifier = Modifier.fillMaxWidth(), value = email,
                onValueChange = {
                    viewModel.setEmail(it)
                },
                label = "Email", maxLines = 1, keyboardType = KeyboardType.Email,
            )

            CustomTextFieldComponent(
                modifier = Modifier.fillMaxWidth(), value = password,
                onValueChange = {
                    viewModel.setPassword(it)
                },
                label = "Senha",
                maxLines = 1, keyboardType = KeyboardType.Password,
            )

            CustomTextFieldComponent(
                modifier = Modifier.fillMaxWidth(), value = confirmPassword,
                onValueChange = {
                    viewModel.setConfirmPassword(it)
                },
                label = "Confirmar senha",
                maxLines = 1, keyboardType = KeyboardType.Password,
            )
            RegisterButton(
                context,
                state = state, isValidPassword = isValidPassword,
                onClick = {
                    viewModel.registerUser(context)
                },
            )
        }
    }
}

@Composable
fun RegisterButton(
    context: Context,
    state: AuthUiState,
    isValidPassword: Boolean,
    onClick: () -> Unit,
) {

    LaunchedEffect(state) {
        if (state is AuthUiState.Error) {
            Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
        }
    }

    ElevatedButton(
        onClick = onClick, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = if (isValidPassword) Purple40 else PurpleGrey40,
        ), modifier = Modifier.fillMaxWidth(), enabled = state !is AuthUiState.Loading
    ) {
        when (state) {
            is AuthUiState.Loading -> {
                CircularProgressIndicator(
                    color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp),
                )
            }

            else -> {
                Text("Cadastrar", color = Color.White)
            }
        }
    }
}