package com.nicolasmorais.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolasmorais.todolistapp.ui.theme.TodoListAppTheme
import com.nicolasmorais.todolistapp.view.SaveTask
import com.nicolasmorais.todolistapp.view.TaskList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "taskList"
                ) {
                    composable(route = "taskList") {
                        TaskList(navController)
                    }
                    composable(route = "saveTask") {
                        SaveTask(navController)
                    }
                }
            }
        }
    }
}
