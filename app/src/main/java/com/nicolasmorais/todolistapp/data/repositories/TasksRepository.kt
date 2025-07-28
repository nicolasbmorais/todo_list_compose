package com.nicolasmorais.todolistapp.data.repositories

import com.nicolasmorais.todolistapp.data.datasource.TaskDataSource
import com.nicolasmorais.todolistapp.data.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TasksRepository {
    private val taskDataSource = TaskDataSource()

    fun saveTask(task: TaskModel, userId: String) {
        taskDataSource.saveTasks(task.title, task.description, task.priority, userId)
    }

    fun getTaskList(userId: String): Flow<MutableList<TaskModel>> {
        return taskDataSource.getTaskList(userId)
    }

    fun deleteTask(task: String) {
        taskDataSource.deleteTask(task)
    }
}