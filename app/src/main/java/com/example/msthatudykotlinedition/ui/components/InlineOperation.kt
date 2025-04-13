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
import com.example.msthatudykotlinedition.utils.OperationState
import net.objecthunter.exp4j.ExpressionBuilder

class InlineOperationView(private val operation: OperationState) {

    @Composable
    fun InlineOperationComposable() {
        Text(
            text = operation.toString(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Log.d("InlineOperationView", "Recomposing operation: ${operation.toString()}")
    }
}



@Composable
fun InlineOperation(operationState: OperationState) {
    InlineOperationView(operationState).InlineOperationComposable()
}






                }
