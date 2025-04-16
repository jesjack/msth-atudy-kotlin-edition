package com.example.msthatudykotlinedition.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.msthatudykotlinedition.data.UserExperienceRepository
import com.example.msthatudykotlinedition.ui.components.CalculatorGrid
import com.example.msthatudykotlinedition.ui.components.DebugButton
import com.example.msthatudykotlinedition.ui.components.InlineOperation
import com.example.msthatudykotlinedition.ui.components.MainContent
import com.example.msthatudykotlinedition.ui.components.OperationDisplay
import com.example.msthatudykotlinedition.ui.components.XPDisplay
import com.example.msthatudykotlinedition.utils.OperationGenerator
import com.example.msthatudykotlinedition.utils.OperationState

class CalculatorScreen {
    private lateinit var result: OperationState
    private lateinit var problemOperation: OperationState
    private lateinit var generator: OperationGenerator
    private var generateNewProblem = mutableStateOf(true)

    @Composable
    fun Render() {
        result = remember { OperationState() }
        problemOperation = remember { OperationState() }

        val context = LocalContext.current
        val userExperienceRepository = remember { UserExperienceRepository(context) }
        val userExperience = remember { mutableIntStateOf(userExperienceRepository.getExperience()) }

        LaunchedEffect(generateNewProblem.value) {
            if (generateNewProblem.value) {
                generator = generateNewProblem(userExperience.value)
                generateNewProblem.value = false
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // Mostrar el contador de experiencia en la esquina superior derecha
            XPDisplay(
                Modifier
                    .align(Alignment.TopEnd),
                xp = userExperience
            )()

            DebugButton(
                Modifier
                    .align(Alignment.TopStart),
            )()

            MainContent()() {
                val problemInlineOperation = InlineOperation(operation = problemOperation)
                val resultInlineOperation = InlineOperation(operation = result)

                OperationDisplay(
                    op1 = problemInlineOperation,
                    op2 = resultInlineOperation,
                )()

                Spacer(modifier = Modifier.height(32.dp))



                CalculatorGrid { text ->
                    Log.d("CalculatorScreen", "Button clicked: $text")
                    when (text) {
                        "<-" -> {
                            if (result.isNotEmpty()) {
                                result.dropLast(1)
                            }
                        }
                        else -> {
                            result.addOperation(text)
                        }
                    }

                    if (result == problemOperation) {
                        // Si la operación es correcta, agregar 1 a la experiencia y borrar la operación
                        userExperienceRepository.addExperience(1)
                        userExperience.value = userExperienceRepository.getExperience()
                        generator.saveOperation()
                        result.clear()
                        problemOperation.clear()
                        generateNewProblem.value = true
                    }
                }()
            }
        }
    }


    private fun generateNewProblem(userExperience: Int): OperationGenerator {
        val generator = OperationGenerator.generateRandomOperation(
            maxValue = userExperience.toFloat(),
            minValue = 1f,
            onlyInts = true
        )
        Log.d("CalculatorScreen", "Generated operation: $generator")

        val components = listOf(
            generator.number1.toString(),
            generator.operation,
            generator.number2.toString()
        )

        components.forEach { component ->
            component
                .removeSuffix(".0")
                .replace(" ", "")
                .forEach { char ->
                    problemOperation.addOperation(char.toString())
                }
        }

        return generator
    }
}
