package com.example.msthatudykotlinedition.utils

import android.content.Context
import com.example.msthatudykotlinedition.helpers.DatabaseHelper
import kotlin.random.Random
import kotlin.times

class OperationGenerator(
    val number1: Float,
    val number2: Float,
    val operation: String,
) {

    fun getResult(): Float {
        return when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> if (number2 != 0f) number1 / number2 else Float.NaN // Manejo de división por cero
            else -> Float.NaN // Operación no válida
        }
    }

    fun getKey(): String {
        return "$number1$operation$number2" // Crea una clave única para la operación
    }

    fun saveOperation() {
        val operationKey = getKey()
        if (!dbHelper.operationExists(operationKey)) {
            dbHelper.insertOperation(operationKey) // Guarda la operación en la base de datos
        }
    }

    override fun toString(): String {
        return "$number1 $operation $number2"
    }

    companion object {
        private lateinit var dbHelper: DatabaseHelper

        fun initializeDatabase(context: Context) {
            dbHelper = DatabaseHelper(context)
        }

        fun generateRandomOperation(
            onlyInts: Boolean = false,
            maxValue: Float = 100f,
            minValue: Float = 0f,
        ): OperationGenerator {
            val operations = listOf("+", "-", "*", "/")
            var number1: Float
            var number2: Float
            var operation: String
            var operationKey: String

            do {
                number1 = Random.nextFloat() * (maxValue - minValue) + minValue
                number2 = Random.nextFloat() * (maxValue - minValue) + minValue
                operation = operations.random() // Selecciona una operación aleatoria
                when (operation) {
                    "-" -> {
                        number1 = number1 * 0.83f
                        number2 = number1 * 0.83f
                    }
                    "*" -> {
                        number1 = number1 * 0.4f
                        number2 = number1 * 0.4f
                    }
                    "/" -> {
                        number1 = number1 * 0.29f
                        number2 = number1 * 0.29f

                        // Reduce la probabilidad de divisores cercanos a 0
                        if (number2 in -0.1f..0.1f) {
                            number2 = (Random.nextFloat() * (maxValue - minValue) + minValue) * 0.29f
                        }
                    }
                }
                if (onlyInts) {
                    number1 = number1.toInt().toFloat() // Convierte a entero
                    number2 = number2.toInt().toFloat() // Convierte a entero
                }
                operationKey = "$number1$operation$number2" // Crea una clave única para la operación
            } while (dbHelper.operationExists(operationKey)) // Repite si la operación ya existe en la base de datos

//            dbHelper.insertOperation(operationKey) // Guarda la operación en la base de datos
            return OperationGenerator(number1, number2, operation)
        }
    }
}
