package com.nicolasmorais.todolistapp.data.model

data class TaskModel(
    val id: String = "",
    val title: String,
    val description: String,
    val priority: Int,
) {
    companion object {
        fun empty(): TaskModel {
            return TaskModel(
                id = "",
                title = "",
                description = "",
                priority = 0
            )
        }
    }
}
