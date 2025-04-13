package com.example.msthatudykotlinedition.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon as MaterialIcon
import com.example.msthatudykotlinedition.utils.icon
import com.shirishkoirala.fontawesome.Icons

@Composable
fun Icon(name: String,
         modifier: Modifier = Modifier.size(24.dp)) {
    val painter: Painter = icon(name)
    MaterialIcon(
        painter = painter,
        contentDescription = name,
        modifier = modifier,
    )
}

val dict = mapOf(
    "+" to Icons.plus,
    "-" to Icons.minus,
    "*" to Icons.x,
    "/" to Icons.divide,
    "=" to Icons.equals,
    "." to Icons.circle_dot,
    "0" to Icons._0,
    "1" to Icons._1,
    "2" to Icons._2,
    "3" to Icons._3,
    "4" to Icons._4,
    "5" to Icons._5,
    "6" to Icons._6,
    "7" to Icons._7,
    "8" to Icons._8,
    "9" to Icons._9,
    "<-" to Icons.arrow_left,
    "±∞" to "±∞",
)