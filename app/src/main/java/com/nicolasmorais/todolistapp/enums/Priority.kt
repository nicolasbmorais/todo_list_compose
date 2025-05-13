package com.nicolasmorais.todolistapp.enums


enum class Priority(val value: Int) {
    NO_PRIORITY(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    companion object {
        fun fromValue(value: Int): Priority {
            return entries.find { it.value == value } ?: NO_PRIORITY
        }
    }
}
