package com.example.msthatudykotlinedition.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun operationExists(operationKey: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT 1 FROM $TABLE_NAME WHERE $COLUMN_OPERATION = ?", arrayOf(operationKey))
        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun insertOperation(operationKey: String) {
        val db = writableDatabase
        db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_OPERATION) VALUES (?)", arrayOf(operationKey))
    }

    companion object {
        private const val DATABASE_NAME = "operations.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "operations"
        private const val COLUMN_OPERATION = "operation"

        private const val CREATE_TABLE_QUERY = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_OPERATION TEXT PRIMARY KEY
            )
        """
    }
}