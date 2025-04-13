package com.example.msthatudykotlinedition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.msthatudykotlinedition.ui.theme.MsthAtudyKotlinEditionTheme
import com.example.msthatudykotlinedition.ui.screens.CalculatorScreen
import com.example.msthatudykotlinedition.utils.OperationGenerator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OperationGenerator.initializeDatabase(this)
        setContent {
            MsthAtudyKotlinEditionTheme {
                CalculatorScreen().Render()
            }
        }
    }
}
