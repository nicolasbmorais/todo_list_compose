package com.nicolasmorais.todolistapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.nicolasmorais.todolistapp.model.TaskModel
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_ENABLED

class TasksViewModel : ViewModel() {


    private val _taskList = mutableStateOf<List<TaskModel>>(emptyList())
    val taskList: State<List<TaskModel>> = _taskList

//    private val _taskList: MutableList<TaskModel> = mutableListOf(
//        TaskModel(
//            task = "Ir ao cinema",
//            description = "Ir ao cinema assistir o filme X",
//            priority = 1,
//        ),
//        TaskModel(
//            task = "Ir ao shopping",
//            description = "Ir ao shopping comer",
//            priority = 0,
//        ),
//        TaskModel(
//            task = "Tarefa 3",
//            description = "Ir ao shopping comer",
//            priority = 3,
//        ),
//        TaskModel(
//            task = "Tarefa 4",
//            description = "Ir ao shopping comer",
//            priority = 4,
//        ),
//        TaskModel(
//            task = "Tarefa 5",
//            description = "Ir ao shopping comer",
//            priority = 2,
//        ),
//    )

//    val taskList get() = _taskList


    fun priorityLevel(priority: Int): String {
        return when (priority) {
            0 -> "Sem prioridade"
            1 -> "Prioridade Baixa"
            2 -> "Prioridade Média"
            else -> "Prioridade Alta"
        }
    }

    fun priorityColor(priority: Int): Color {
        return when (priority) {
            0 -> Color.Black
            2 -> RADIO_BUTTON_GREEN_ENABLED
            3 -> RADIO_BUTTON_YELLOW_ENABLED
            else -> RADIO_BUTTON_RED_ENABLED
        }
    }
}