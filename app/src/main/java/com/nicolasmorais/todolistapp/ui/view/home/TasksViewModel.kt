package com.nicolasmorais.todolistapp.ui.view.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.enums.Priority
import com.nicolasmorais.todolistapp.data.model.TaskModel
import com.nicolasmorais.todolistapp.data.repositories.TasksRepository
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_ENABLED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TasksViewModel : ViewModel() {

    private val _repository = TasksRepository()

    private val _taskList = MutableStateFlow<List<TaskModel>>(emptyList())
    val taskList = _taskList.asStateFlow()


    init {
        viewModelScope.launch {
            try {
                _repository.getTaskList().collectLatest {
                    _taskList.value = it
                }
            } catch (e: Exception) {
                Log.e("TasksViewModel", "Error: ${e.message}")
            }
        }
    }


    var showDeleteDialog by mutableStateOf(false)

    var taskTitle by mutableStateOf("")
        private set

    var taskDescription by mutableStateOf("")
        private set

    var taskPriority by mutableStateOf(Priority.NO_PRIORITY)
        private set

    val isValid: Boolean
        get() = taskTitle.isNotBlank() && taskDescription.isNotBlank()


    private fun fetchTasks() {
        viewModelScope.launch {
            _repository.getTaskList().collect {
                _taskList.value = it
            }
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            _repository.deleteTask(taskId)
        }
    }


    fun onTitleChanged(value: String) {
        taskTitle = value
    }

    fun onDescriptionChanged(value: String) {
        taskDescription = value
    }


    fun setPriority(priority: Priority) {
        taskPriority = if (taskPriority == priority) Priority.NO_PRIORITY else priority
    }


    fun saveTask() {
        val task = TaskModel(
            title = taskTitle, description = taskDescription, priority = taskPriority.value
        )

        viewModelScope.launch {
            _repository.saveTask(task)
        }
    }

    fun priorityLevel(priority: Int, context: Context): String {
        val value = when (priority) {
            0 -> context.getString(R.string.sem_prioridade)
            1 -> context.getString(R.string.prioridade_baixa)
            2 -> context.getString(R.string.prioridade_media)
            else -> context.getString(R.string.prioridade_alta)
        }
        return value
    }

    fun priorityColor(priority: Int): Color {
        return when (priority) {
            0 -> Color.Black
            1 -> RADIO_BUTTON_GREEN_ENABLED
            2 -> RADIO_BUTTON_YELLOW_ENABLED
            else -> RADIO_BUTTON_RED_ENABLED
        }
    }


}
