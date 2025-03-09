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
            put("progress", userProgress.progress)
        }
        val id = db.insertWithOnConflict("user_progress", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
        return id
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
            val progressValue = cursor.getInt(cursor.getColumnIndexOrThrow("progress"))
            progress = UserProgress(userId, lessonId, progressValue)
        }

        cursor.close()
        db.close()
        return progress
    }
}
