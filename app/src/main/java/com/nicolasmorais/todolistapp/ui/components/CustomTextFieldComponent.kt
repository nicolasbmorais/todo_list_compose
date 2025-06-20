package com.nicolasmorais.todolistapp.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.nicolasmorais.todolistapp.ui.theme.BLACK
import com.nicolasmorais.todolistapp.ui.theme.LIGHT_BLUE
import com.nicolasmorais.todolistapp.ui.theme.ShapeEditText
import com.nicolasmorais.todolistapp.ui.theme.WHITE


@Composable
fun CustomTextFieldComponent(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    maxLines: Int,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = BLACK,
            focusedLabelColor = LIGHT_BLUE,
            cursorColor = LIGHT_BLUE,
            focusedContainerColor = WHITE,
            disabledContainerColor = WHITE,
            errorContainerColor = WHITE,
            unfocusedContainerColor = WHITE,
        ),
        shape = ShapeEditText.small,
    )
}
