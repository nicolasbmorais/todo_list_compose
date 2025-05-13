package com.nicolasmorais.todolistapp.repositories

import com.nicolasmorais.todolistapp.data.DataSource
import com.nicolasmorais.todolistapp.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TasksRepository() {
    private val dataSource = DataSource()

    fun saveTask(task: TaskModel) {
        dataSource.saveTasks(task.title, task.description, task.priority)
    }

    fun getTaskList(): Flow<MutableList<TaskModel>> {
        return dataSource.getTaskList()
    }
}