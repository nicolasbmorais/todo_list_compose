package com.nicolasmorais.todolistapp.data.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nicolasmorais.todolistapp.data.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskDataSource {
    private val db = FirebaseFirestore.getInstance()

    private val _allTasks = MutableStateFlow<MutableList<TaskModel>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<TaskModel>> = _allTasks

    fun saveTasks(title: String, description: String, priority: Int) {
        Log.d("DataSource", "Iniciando o salvamento da tarefa")

        val taskMap = hashMapOf(
            "title" to title, "description" to description, "priority" to priority
        )

        db.collection("tasks").document(title).set(taskMap).addOnCompleteListener {
            Log.d("Salvando tarefa", "Tarefa salva com sucesso $taskMap - Result: ${it.result}")
        }.addOnFailureListener {
            Log.d("Erro ao salvar tarefa", "Tarefa não foi salva: ${it.message}")
        }
    }

    fun getTaskList(): Flow<MutableList<TaskModel>> {
        db.collection("tasks").addSnapshotListener { snapshot, error ->
            if (error != null) {
                Log.e("DataSource", "Erro ao escutar tarefas: ${error.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val tasks = snapshot.documents.map { document ->
                    TaskModel(
                        title = document.getString("title") ?: "Title",
                        description = document.getString("description") ?: "Description",
                        priority = document.getLong("priority")?.toInt() ?: 0,
                    )
                }.toMutableList()

                _allTasks.value = tasks
            }
        }

        return allTasks
    }

    fun deleteTask(task: String) {
        db.collection("tasks").document(task).delete().addOnCompleteListener {
            Log.d("Firestore", "Tarefa deletada com sucesso")
        }.addOnFailureListener {
            Log.e("Firestore", "Erro ao deletar tarefa: ${it.message}")
        }


        db.collection("tasks").document(task).delete().addOnCompleteListener {
            Log.d("Firestore", "Tarefa deletada com sucesso")
        }.addOnFailureListener {
            Log.e("Firestore", "Erro ao deletar tarefa: ${it.message}")
        }
    }
}