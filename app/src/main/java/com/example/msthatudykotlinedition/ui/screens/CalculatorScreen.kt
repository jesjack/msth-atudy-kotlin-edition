package com.example.msthatudykotlinedition.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.msthatudykotlinedition.ui.components.CalculatorGrid
import com.example.msthatudykotlinedition.ui.components.InlineOperationView
import com.example.msthatudykotlinedition.data.UserExperienceRepository
import com.example.msthatudykotlinedition.utils.OperationGenerator
import com.example.msthatudykotlinedition.utils.OperationState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class CalculatorScreen {
    private var result by mutableStateOf(OperationState())
    private lateinit var problemOperation: OperationState

    @Composable
    fun Render() {        
        Log.d("CalculatorScreen", "result created: $result")
        problemOperation = remember { OperationState() }

        val context = LocalContext.current
        val userExperienceRepository = remember { UserExperienceRepository(context) }
        val userExperience = remember { userExperienceRepository.getExperience() }

        LaunchedEffect(Unit) {
            Log.d("CalculatorScreen", "Launched effect triggered, generating initial problem")
            generateNewProblem(userExperience)
            Log.d("CalculatorScreen", "Generated initial problem")

        }

        Box(modifier = Modifier.fillMaxSize()) {
            // Mostrar el contador de experiencia en la esquina superior derecha
            Text(
                text = "XP: $userExperience",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d("CalculatorScreen", "Recomposition: result=$result, problemOperation=$problemOperation")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InlineOperationView.InlineOperationComposable(operation = problemOperation)

                     Spacer(modifier = Modifier.width(8.dp))
                    InlineOperation(operation = listOf("=")).Render()
                    Spacer(modifier = Modifier.width(8.dp))
                    resultInlineOperation.Render()
                }

                Spacer(modifier = Modifier.height(32.dp))

                val buttons = listOf(
                    listOf(null, "±∞", "<-"),
                    listOf("7", "8", "9"),
                    listOf("4", "5", "6"),
                    listOf("1", "2", "3"),
                    listOf(".", "0", "-")
                )

                CalculatorGrid(buttons = buttons) { text ->
                    Log.d("CalculatorScreen", "Button clicked: $text")
                    when (text) {
                        "<-" -> {
                            if (result.isNotEmpty()) {
                                Log.d("CalculatorScreen", "result dropLast")
                                //result.dropLast(1)
                            }
                        }
                        else -> {
                            Log.d("CalculatorScreen", "result addOperation: $text")
                           // result.addOperation(text)
                            
                        }

                    }

//                    if (result == problemOperation) {
//                        // Si la operación es correcta, agregar 1 a la experiencia y borrar la operación
//                        userExperienceRepository.addExperience(1)
//                        result.clear()
//                        problemOperation.clear()
//                        generateNewProblem(userExperience + 1)
//                    }
                }
            }
        }
    }

    

    private fun generateNewProblem(userExperience: Int) {
        val generator = OperationGenerator.generateRandomOperation(
            maxValue = userExperience.toFloat(),
            minValue = 1f
        )
        Log.d("CalculatorScreen", "generateNewProblem() Generated operation: $generator")
        problemOperation.set(
            listOf(
                generator.number1.toInt().toString(),
                generator.operation,
                generator.number2.toInt().toString()
            )
        )        
        Log.d("CalculatorScreen", "generateNewProblem() problemOperation setted: $problemOperation")

    }
}
