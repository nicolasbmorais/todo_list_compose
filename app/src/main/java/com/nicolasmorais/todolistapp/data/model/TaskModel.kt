package com.nicolasmorais.todolistapp.data.model

data class TaskModel(
    val title: String,
    val description: String,
    val priority: Int,
) {
    companion object {
        fun empty(): TaskModel {
            return TaskModel(
                title = "",
                description = "",
                priority = 0
            )
        }
    }
}
