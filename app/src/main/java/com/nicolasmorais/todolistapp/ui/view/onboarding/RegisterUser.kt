package com.nicolasmorais.todolistapp.ui.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.ui.view.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterUser(
    navController: NavController,
    viewModel: LoginViewModel = viewModel(),
) {
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

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {
                viewModel.setEmail(it)
            }, label = { Text("Email") })

            TextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {
                viewModel.setEmail(it)
            }, label = { Text("Senha") })

            TextField(modifier = Modifier.fillMaxWidth(), value = "", onValueChange = {
                viewModel.setConfirmPassword(it)
            }, label = { Text("Confirmar senha") })

            Button(
                onClick = {}, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cadastrar")
            }
        }
    }
}