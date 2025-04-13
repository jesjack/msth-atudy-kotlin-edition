package com.example.msthatudykotlinedition.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserExperienceRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_experience_prefs", Context.MODE_PRIVATE)

    fun saveExperience(experience: Int) {
        sharedPreferences.edit() {
            putInt("user_experience", experience)
        }
    }

    fun getExperience(): Int {
        return sharedPreferences.getInt("user_experience", 0)
    }

    fun addExperience(experience: Int) {
        val currentExperience = getExperience()
        saveExperience(currentExperience + experience)
    }
}
