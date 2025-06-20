package com.nicolasmorais.todolistapp.ui.view.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.ui.components.CustomTextFieldComponent
import com.nicolasmorais.todolistapp.ui.components.PrimaryButtonComponent
import com.nicolasmorais.todolistapp.enums.Priority
import com.nicolasmorais.todolistapp.ui.theme.Purple700
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskPage(navController: NavController, tasksViewModel: TasksViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.salvar_tarefa),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = WHITE
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700
                )

            )
        },
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            CustomTextFieldComponent(
                value = tasksViewModel.taskTitle,
                label = stringResource(R.string.titulo_tarefa),
                onValueChange = {
                    tasksViewModel.onTitleChanged(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.height(20.dp))
            CustomTextFieldComponent(
                value = tasksViewModel.taskDescription,
                label = stringResource(R.string.descricao),
                onValueChange = {
                    tasksViewModel.onDescriptionChanged(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), maxLines = 20,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.nivel_prioridade))
                RadioButton(
                    selected = tasksViewModel.taskPriority == Priority.LOW,
                    onClick =
                    { tasksViewModel.setPriority(Priority.LOW) },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_ENABLED,
                    )
                )
                RadioButton(
                    selected = tasksViewModel.taskPriority == Priority.MEDIUM,
                    onClick = { tasksViewModel.setPriority(Priority.MEDIUM) },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_ENABLED,
                    )
                )
                RadioButton(
                    selected = tasksViewModel.taskPriority == Priority.HIGH,
                    onClick = { tasksViewModel.setPriority(Priority.HIGH) },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_ENABLED,
                    )
                )


            }
            Spacer(Modifier.height(20.dp))
            PrimaryButtonComponent(
                text = stringResource(R.string.salvar),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    scope.launch {

                        if (!tasksViewModel.isValid) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.titulo_tarefa_obrigatorio),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@launch
                        }

                        withContext(Dispatchers.IO) {
                            tasksViewModel.saveTask()
                        }

                        Toast.makeText(
                            context,
                            context.getString(R.string.sucesso_ao_salvar_tarefa), Toast.LENGTH_SHORT
                        ).show()

                        navController.popBackStack()
                    }
                },

                )
        }
    }
}
