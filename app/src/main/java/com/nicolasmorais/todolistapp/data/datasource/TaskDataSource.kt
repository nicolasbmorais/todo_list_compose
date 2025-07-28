package com.nicolasmorais.todolistapp.data.datasource

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.nicolasmorais.todolistapp.data.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskDataSource {
    private val db = FirebaseFirestore.getInstance()

    private val _tasks = MutableStateFlow<MutableList<TaskModel>>(mutableListOf())
    private val tasks: StateFlow<MutableList<TaskModel>> = _tasks
    private var listenerRegistration: ListenerRegistration? = null

    fun saveTasks(title: String, description: String, priority: Int, uid: String) {
        Log.d("TaskDataSource", "Iniciando o salvamento da tarefa")

        val taskMap = hashMapOf(
            "title" to title,
            "description" to description,
            "priority" to priority,
            "userId" to uid,
            "createdAt" to FieldValue.serverTimestamp()
        )

        db.collection("tasks").add(taskMap).addOnCompleteListener {
            Log.d("Salvando tarefa", "Tarefa salva com sucesso $taskMap - Result: ${it.result}")
        }.addOnFailureListener {
            Log.d("Erro ao salvar tarefa", "Tarefa não foi salva: ${it.message}")
        }
    }

    fun getTaskList(userId: String): Flow<MutableList<TaskModel>> {
        listenerRegistration?.remove()

        listenerRegistration = db.collection("tasks").whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("DataSource", "Erro ao escutar tarefas: ${error.message}")
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val tasks = snapshot.documents.map { document ->
                        TaskModel(
                            id = document.id,
                            title = document.getString("title") ?: "Sem título",
                            description = document.getString("description") ?: "Sem descrição",
                            priority = document.getLong("priority")?.toInt() ?: 0
                        )
                    }.toMutableList()


                    _tasks.value = tasks
                }
            }

        return tasks
    }

    fun deleteTask(documentId: String) {
        db.collection("tasks").document(documentId).delete().addOnSuccessListener {
            Log.d("Firestore", "Tarefa deletada com sucesso")
        }.addOnFailureListener {
            Log.e("Firestore", "Erro ao deletar tarefa: ${it.message}")
        }
    }
}