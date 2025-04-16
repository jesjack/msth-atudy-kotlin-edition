package com.example.msthatudykotlinedition.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainContent(
    extraModifier: Modifier? = null
) : BaseComposable(extraModifier) {
    @Composable
    override operator fun invoke(
        content: (@Composable () -> Unit)?
    ) {
        Column(
            modifier = (extraModifier ?: Modifier)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content?.invoke()
        }
    }
}
