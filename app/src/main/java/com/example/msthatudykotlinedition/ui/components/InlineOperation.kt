package com.example.msthatudykotlinedition.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shirishkoirala.fontawesome.Icons
import net.objecthunter.exp4j.ExpressionBuilder

class InlineOperation(
    private val operation: List<String>,
    extraModifier: Modifier? = null
) : BaseComposable(extraModifier) {

    @Composable
    override operator fun invoke(
        content: (@Composable () -> Unit)?
    ) {
        Row(
            modifier = (extraModifier ?: Modifier),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            operation.forEachIndexed { index, currentChar ->
                if (currentChar == "±∞") {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy((-12).dp),
                        ) {
                            Text(
                                text = "~",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Icon(name = Icons.infinity)
                        }
                    }
                } else {
                    val iconName = dict[currentChar] ?: ""
                    Icon(name = iconName)
                }

//                if (index < operation.size - 1) {
//                    Spacer(modifier = Modifier.width(8.dp))
//                }
            }
        }
    }
}
