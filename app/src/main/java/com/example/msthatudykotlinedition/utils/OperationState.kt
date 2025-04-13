package com.example.msthatudykotlinedition.utils

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import net.objecthunter.exp4j.ExpressionBuilder

class OperationState : MutableList<String> by mutableStateListOf() {

    fun evaluate(): Float {
        return try {
            val expression = this.joinToString("")
            ExpressionBuilder(expression).build().evaluate().toFloat()
        } catch (e: Exception) {
            Float.NaN
        }
    }

    private fun get(): List<String> {
        return this.toList()
    }

    fun set(newState: List<String>) {
        this.clear()
        this.addAll(newState)
    }

    fun isNotEmpty(): Boolean {
        return this.get().isNotEmpty()
    }

    fun dropLast(n: Int) {
        if (this.size >= n) {
            this.removeAt(this.size - n)
        } else {
            Log.e("OperationState", "Cannot drop $n elements from a list of size ${this.size}")
        }
    }

    fun addOperation(operation: String) {
        this.add(operation)
    }

//    OVERRIDES

    override fun equals(other: Any?): Boolean {
        Log.d("OperationState", "Comparing $this with $other")
        if (other !is OperationState) return false
        return this.evaluate() == other.evaluate()
    }

    override fun hashCode(): Int {
        return this.joinToString("").hashCode()
    }

    override fun toString(): String {
        return this.evaluate().toString()
    }
}
