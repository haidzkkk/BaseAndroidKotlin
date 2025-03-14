package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.app.langking.data.model.UserProgress

class UserProgressDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun updateProgress(userProgress: UserProgress): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_id", userProgress.userId)
            put("lesson_id", userProgress.lessonId)
            put("progress", userProgress.score)
            put("date_test", userProgress.dateTest)
            put("date_start", userProgress.dateStart)
        }
        val id = db.insertWithOnConflict("user_progress", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
        return id
    }


    fun getUserProgress(userId: Int): List<UserProgress> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val progressList = mutableListOf<UserProgress>()

        val cursor: Cursor = db.query(
            "user_progress",
            null,
            "user_id = ?",
            arrayOf(userId.toString()),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val lessonId = cursor.getInt(cursor.getColumnIndexOrThrow("lesson_id"))
                val score = cursor.getInt(cursor.getColumnIndexOrThrow("progress"))
                val dateTest = cursor.getString(cursor.getColumnIndexOrThrow("date_test"))
                val dateStart = cursor.getString(cursor.getColumnIndexOrThrow("date_start"))

                val progress = UserProgress(userId, lessonId, score, dateTest, dateStart)
                progressList.add(progress)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return progressList
    }

    fun getUserProgress(userId: Int, lessonId: Int): UserProgress? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "user_progress",
            null,
            "user_id = ? AND lesson_id = ?",
            arrayOf(userId.toString(), lessonId.toString()),
            null, null, null
        )

        var progress: UserProgress? = null
        if (cursor.moveToFirst()) {
            val score = cursor.getInt(cursor.getColumnIndexOrThrow("progress"))
            val dateTest = cursor.getString(cursor.getColumnIndexOrThrow("date_test"))
            val dateStart = cursor.getString(cursor.getColumnIndexOrThrow("date_start"))
            progress = UserProgress(userId, lessonId, score, dateTest, dateStart)
        }

        cursor.close()
        db.close()
        return progress
    }

}
