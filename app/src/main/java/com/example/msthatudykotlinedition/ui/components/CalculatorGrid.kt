package com.example.msthatudykotlinedition.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shirishkoirala.fontawesome.Icons

class CalculatorGrid(
    private val buttonSize: Dp = 56.dp,
    private val buttonSpacing: Dp = 8.dp,
    extraModifier: Modifier? = null,
    private val onButtonClick: (String) -> Unit
) : BaseComposable(extraModifier) {

    @Composable
    override operator fun invoke(
        content: (@Composable () -> Unit)?
    ) {
        val buttons = listOf(
            listOf(null, "±∞", "<-"),
            listOf("7", "8", "9"),
            listOf("4", "5", "6"),
            listOf("1", "2", "3"),
            listOf(".", "0", "-")
        )
        Column(
            modifier = (extraModifier ?: Modifier).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            buttons.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    row.forEachIndexed { index, text ->
                        if (text != null) {
                            Button(
                                onClick = { onButtonClick(text) },
                                modifier = Modifier.size(buttonSize),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF000000)
                                )
                            ) {
                                if (dict[text] == "±∞") {
                                    Box(
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy((-12).dp),
                                        ) {
                                            Text(
                                                text = "~",
                                                style = TextStyle(
                                                    color = Color.White,
                                                    fontSize = 24.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.Center
                                                )
                                            )
                                            Icon(name = Icons.infinity)
                                        }
                                    }
                                } else {
                                    Icon(name = dict[text] ?: "")
                                }
                            }
                        } else {
                            Spacer(modifier = Modifier.size(buttonSize))
                        }
                        if (index < row.size - 1) {
                            Spacer(modifier = Modifier.width(buttonSpacing))
                        }
                    }
                }
            }
        }
    }
}

