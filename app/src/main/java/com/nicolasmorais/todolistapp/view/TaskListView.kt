package com.nicolasmorais.todolistapp.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.components.TaskItemComponent
import com.nicolasmorais.todolistapp.repositories.TasksRepository
import com.nicolasmorais.todolistapp.ui.theme.BLACK
import com.nicolasmorais.todolistapp.ui.theme.Purple700
import com.nicolasmorais.todolistapp.ui.theme.WHITE
import com.nicolasmorais.todolistapp.viewmodel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
    navController: NavController,
    taskViewModel: TasksViewModel = viewModel<TasksViewModel>()
) {

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
                )

            )
        },
        containerColor = BLACK,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("saveTask") },
                containerColor = Purple700
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")

            }
        }
    ) { paddingValues ->
        val taskList = taskViewModel.getAllTasks().collectAsState(mutableListOf()).value

        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(taskList) { _, task ->
                TaskItemComponent(
                    taskTitle = task.title ?: "",
                    taskDescription = task.description ?: "",
                    taskPriority = task.priority ?: 0
                )
            }
        }


    }
}

