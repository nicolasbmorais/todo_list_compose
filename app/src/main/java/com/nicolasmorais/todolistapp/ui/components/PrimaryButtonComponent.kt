package com.nicolasmorais.todolistapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolasmorais.todolistapp.ui.theme.Purple40
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButtonComponent(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    backgroundColor: Color = Purple40,
    contentColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(10.dp),
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = shape,
        enabled = isEnabled && !isLoading,
        colors = ButtonDefaults.elevatedButtonColors(containerColor = backgroundColor),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = contentColor
            )
        }
    }
}
