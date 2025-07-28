package com.nicolasmorais.todolistapp.ui.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.Destinations
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.ui.components.TaskItemComponent
import com.nicolasmorais.todolistapp.ui.theme.Purple40
import com.nicolasmorais.todolistapp.ui.theme.Purple700
import com.nicolasmorais.todolistapp.ui.theme.WHITE
import com.nicolasmorais.todolistapp.ui.view.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    taskViewModel: TasksViewModel = viewModel<TasksViewModel>(),
    authViewModel: AuthViewModel = viewModel<AuthViewModel>(),
) {

    val user by authViewModel.currentUser.collectAsState()
    val taskList by taskViewModel.taskList.collectAsState()

    LaunchedEffect(user) {
        user?.uid?.let { uid ->
            taskViewModel.loadTasks(uid)
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.lista_de_tarefas),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WHITE
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700
                ),
                actions = {
                    Icon(
                        Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = stringResource(R.string.sair_do_aplicativo),
                        Modifier.clickable {
                            authViewModel.signOut()
                            navController.navigate(Destinations.LOGIN_ROUTE) {
                                popUpTo(Destinations.HOME_ROUTE) { inclusive = true }
                            }
                        },
                        tint = WHITE,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Destinations.CREATE_TASK_ROUTE) },
                containerColor = Purple40
            ) {
                Icon(
                    Icons.Filled.Add, "Floating action button.",
                    tint = WHITE,
                )

            }
        },
    ) { paddingValues ->
        if (user == null) {
            Text(stringResource(R.string.usuario_nao_encontrado))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(24.dp)
            ) {

                itemsIndexed(taskList) { _, task ->
                    TaskItemComponent(
                        taskTitle = task.title,
                        taskDescription = task.description,
                        taskPriority = task.priority,
                    )
                }
            }
        }
    }
}

