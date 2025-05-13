package com.nicolasmorais.todolistapp.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class DataSource {
    private val db = FirebaseFirestore.getInstance()

    fun saveTasks(task: String, description: String, priority: Int) {
        val taskMap = hashMapOf(
            "task" to task,
            "description" to description,
            "priority" to priority
        )

        db.collection("tasks").document(task).set(taskMap).addOnCompleteListener {
            Log.d("Salvando tarefa", "Tarefa salva com sucesso $taskMap" + it.result)
        }
            .addOnFailureListener {
                Log.d("Erro ao salvar tarefa", "Tarefa nao foi salva " + it.message)

            }
    }

}