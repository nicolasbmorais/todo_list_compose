package com.nicolasmorais.todolistapp.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.ui.theme.ShapeCardPriority
import com.nicolasmorais.todolistapp.ui.theme.WHITE
import com.nicolasmorais.todolistapp.ui.view.home.TasksViewModel

@Composable
fun TaskItemComponent(
    taskTitle: String,
    taskDescription: String,
    taskPriority: Int,
) {
    val taskViewModel: TasksViewModel = viewModel()
    val context = LocalContext.current

    Card(
        colors = CardColors(
            containerColor = WHITE,
            contentColor = WHITE,
            disabledContainerColor = WHITE,
            disabledContentColor = WHITE
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(30.dp, 20.dp)
        ) {
            val (txtTitle, txtDescription, cardPriority, txtPriority, btnDelete) = createRefs()

            Text(taskTitle,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })

            Text(
                taskDescription,
                color = Color.Black,
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                })

            Text(
                taskViewModel.priorityLevel(taskPriority, context),
                color = Color.Black,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                })

            Card(
                colors = CardColors(
                    containerColor = taskViewModel.priorityColor(taskPriority),
                    contentColor = taskViewModel.priorityColor(taskPriority),
                    disabledContainerColor = WHITE,
                    disabledContentColor = WHITE
                ),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom)
                    },
                shape = ShapeCardPriority.large
            ) { }

            IconButton(
                onClick = {
                    taskViewModel.showDeleteDialog = true
                },
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(Icons.Filled.Delete, "Delete button.", tint = Color.Red)
            }
        }

    }

    if (taskViewModel.showDeleteDialog) {
        DeleteDialog(
            onDismiss = {
                taskViewModel.showDeleteDialog = false
            },
            onConfirm = {
                taskViewModel.deleteTask(taskTitle)
                taskViewModel.showDeleteDialog = false
                Toast.makeText(
                    context,
                    context.getString(R.string.sucesso_ao_deletar_tarefa),
                    Toast.LENGTH_SHORT
                ).show()

            },
            onCancel = {
                taskViewModel.showDeleteDialog = false
            },
        )
    }

}

@Composable
fun DeleteDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Título do Alerta")
        },
        text = {
            Text("Deseja realmente continuar?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    )
}

