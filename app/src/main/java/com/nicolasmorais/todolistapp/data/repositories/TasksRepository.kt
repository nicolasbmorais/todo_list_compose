package com.nicolasmorais.todolistapp.data.repositories

import com.nicolasmorais.todolistapp.data.datasource.TaskDataSource
import com.nicolasmorais.todolistapp.data.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TasksRepository {
    private val taskDataSource = TaskDataSource()

    fun saveTask(task: TaskModel) {
        taskDataSource.saveTasks(task.title, task.description, task.priority)
    }

    fun getTaskList(): Flow<MutableList<TaskModel>> {
        return taskDataSource.getTaskList()
    }

    fun deleteTask(task: String) {
        taskDataSource.deleteTask(task)
    }
}