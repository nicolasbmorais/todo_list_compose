package com.nicolasmorais.todolistapp.view

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nicolasmorais.todolistapp.R
import com.nicolasmorais.todolistapp.components.CustomTextField
import com.nicolasmorais.todolistapp.components.PrimaryButton
import com.nicolasmorais.todolistapp.ui.theme.Purple700
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_GREEN_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_RED_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.nicolasmorais.todolistapp.ui.theme.RADIO_BUTTON_YELLOW_ENABLED
import com.nicolasmorais.todolistapp.ui.theme.WHITE

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTask(navController: NavController) {
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
        var taskTitle by remember { mutableStateOf("") }
        var taskContent by remember { mutableStateOf("") }
        var noPriority by remember { mutableStateOf(false) }
        var lowPriority by remember { mutableStateOf(false) }
        var midPriority by remember { mutableStateOf(false) }
        var highPriority by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            CustomTextField(
                value = taskTitle,
                label = stringResource(R.string.titulo_tarefa),
                onValueChange = { value ->
                    taskTitle = value
                },
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )
            Spacer(Modifier.height(20.dp))
            CustomTextField(
                value = taskContent,
                label = stringResource(R.string.descricao),
                onValueChange = { value ->
                    taskContent = value
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
                    selected = lowPriority,
                    onClick = { lowPriority = !lowPriority },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_GREEN_DISABLED,
                        selectedColor = RADIO_BUTTON_GREEN_ENABLED,

                        )
                )
                RadioButton(
                    selected = midPriority,
                    onClick = { midPriority = !midPriority },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        selectedColor = RADIO_BUTTON_YELLOW_ENABLED,

                        )
                )
                RadioButton(
                    selected = highPriority,
                    onClick = { highPriority = !highPriority },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RADIO_BUTTON_RED_DISABLED,
                        selectedColor = RADIO_BUTTON_RED_ENABLED,
                    )
                )


            }
            Spacer(Modifier.height(20.dp))
            PrimaryButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                text = stringResource(R.string.salvar)
            )
        }
    }
}