package com.example.msthatudykotlinedition.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class BaseComposable(
    protected val extraModifier: Modifier? = null
) {
    @Composable
    abstract operator fun invoke(
        content: (@Composable () -> Unit)? = null
    )
}
