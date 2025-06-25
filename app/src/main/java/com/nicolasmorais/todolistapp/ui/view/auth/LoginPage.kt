package com.nicolasmorais.todolistapp.ui.view.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolasmorais.todolistapp.Destinations
import com.nicolasmorais.todolistapp.ui.components.CustomTextFieldComponent
import com.nicolasmorais.todolistapp.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel(),
) {

    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()


    val state by viewModel.userState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state is AuthUiState.Error) {
            Toast.makeText(context, (state as AuthUiState.Error).message, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(state) {
        if (state is AuthUiState.Success) {
            navController.navigate(Destinations.HOME_ROUTE) {
                popUpTo(Destinations.LOGIN_ROUTE) { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {})
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
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

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    title = "Entrar",
                    state = state,
                    onClick = {
                        navController.navigate(
                            Destinations.HOME_ROUTE
                        )
                    },
                )
                Button(
                    title = "Cadastrar",
                    state = state,
                    onClick = {
                        navController.navigate(
                            Destinations.REGISTER_USER_ROUTE
                        )
                    },
                )

            }
        }
    }

}


@Composable
fun Button(
    title: String,
    state: AuthUiState,
    onClick: () -> Unit,
) {

    ElevatedButton(
        onClick = onClick, colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Purple40,
        ), modifier = Modifier.fillMaxWidth(), enabled = state !is AuthUiState.Loading
    ) {
        when (state) {
            is AuthUiState.Loading -> {
                CircularProgressIndicator(
                    color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp),
                )
            }

            else -> {
                Text(title, color = Color.White)
            }
        }
    }
}