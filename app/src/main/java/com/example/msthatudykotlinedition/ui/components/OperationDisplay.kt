package com.example.msthatudykotlinedition.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class OperationDisplay(
    extraModifier: Modifier? = null,
    private val op1: BaseComposable,
    private val op2: BaseComposable
) : BaseComposable(extraModifier) {
    @Composable
    override operator fun invoke(
        content: (@Composable () -> Unit)?
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            op1()
            Spacer(modifier = Modifier.width(8.dp))
            InlineOperation(operation = listOf("="))()
            Spacer(modifier = Modifier.width(8.dp))
            op2()
        }
    }
}
