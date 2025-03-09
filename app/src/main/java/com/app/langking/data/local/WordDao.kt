package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.app.langking.data.model.Word

class WordDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertWord(word: Word): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("lesson_id", word.lessonId)
            put("english", word.english)
            put("vietnamese", word.vietnamese)
            put("pronunciation", word.pronunciation)
            put("audio_url", word.audioUrl)
        }
        val id = db.insert("words", null, values)
        db.close()
        return id
    }

    fun getWordsByLesson(lessonId: Int): List<Word> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "words",
            null,
            "lesson_id = ?",
            arrayOf(lessonId.toString()),
            null, null, null
        )

        val words = mutableListOf<Word>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val english = cursor.getString(cursor.getColumnIndexOrThrow("english"))
            val vietnamese = cursor.getString(cursor.getColumnIndexOrThrow("vietnamese"))
            val pronunciation = cursor.getString(cursor.getColumnIndexOrThrow("pronunciation"))
            val audioUrl = cursor.getString(cursor.getColumnIndexOrThrow("audio_url"))

            words.add(Word(id, lessonId, english, vietnamese, pronunciation, audioUrl))
        }
        cursor.close()
        db.close()
        return words
    }
}

