package com.nicolasmorais.todolistapp.data.repositories

import com.nicolasmorais.todolistapp.data.datasource.DataSource
import com.nicolasmorais.todolistapp.data.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TasksRepository {
    private val dataSource = DataSource()

    fun saveTask(task: TaskModel) {
        dataSource.saveTasks(task.title, task.description, task.priority)
    }

    fun getTaskList(): Flow<MutableList<TaskModel>> {
        return dataSource.getTaskList()
    }

    fun deleteTask(task: String) {
        dataSource.deleteTask(task)
    }
}