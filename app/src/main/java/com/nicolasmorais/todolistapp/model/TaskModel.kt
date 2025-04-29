package com.nicolasmorais.todolistapp.model

data class TaskModel(
    val task: String? = null,
    val description: String? = null,
    val priority: Int? = null,
)