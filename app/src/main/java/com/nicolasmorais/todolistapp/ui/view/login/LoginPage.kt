package com.nicolasmorais.todolistapp.ui.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolasmorais.todolistapp.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(),
) {
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
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                value = "",
                onValueChange = {
                    viewModel.setEmail(it)
                },
                label = { Text("Email") })

            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                value = "",
                onValueChange = {
                    viewModel.setEmail(it)
                },
                label = { Text("Senha") })

            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {}, modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar")
                }
                Button(
                    onClick = { navController.navigate(Destinations.REGISTER_USER_ROUTE) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cadastrar")
                }
            }
        }
    }
}