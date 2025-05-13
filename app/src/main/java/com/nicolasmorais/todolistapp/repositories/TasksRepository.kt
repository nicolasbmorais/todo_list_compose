package com.nicolasmorais.todolistapp.repositories

import com.nicolasmorais.todolistapp.data.DataSource

class TasksRepository() {
    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priority: Int) {
        dataSource.saveTasks(task, description, priority)
    }
}