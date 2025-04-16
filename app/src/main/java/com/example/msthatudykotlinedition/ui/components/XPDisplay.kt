package com.example.msthatudykotlinedition.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class XPDisplay(
    extraModifier: Modifier? = null,
    private val xp: MutableState<Int>
) : BaseComposable(extraModifier) {
    @Composable
    override operator fun invoke(
        content: (@Composable () -> Unit)?
    ) {
        Text(
            text = "XP: ${xp.value}",
            modifier = (extraModifier ?: Modifier)
                .padding(16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.End
        )
    }
}
