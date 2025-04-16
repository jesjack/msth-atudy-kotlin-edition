package com.example.msthatudykotlinedition.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shirishkoirala.fontawesome.Icons

class DebugButton(
    extraModifier: Modifier? = null
) : BaseComposable(extraModifier) {
    @Composable
    override fun invoke(content: @Composable (() -> Unit)?) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF000000)
            )
        ) {
            Icon(name = Icons.terminal)
        }
    }
}