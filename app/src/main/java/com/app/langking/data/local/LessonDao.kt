package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.app.langking.data.model.Lesson

class LessonDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertLesson(lesson: Lesson): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", lesson.id)
            put("category_id", lesson.categoryId)
            put("name", lesson.name)
            put("content", lesson.content)
        }
        val id = db.insert("lessons", null, values)
        db.close()
        return id
    }

    fun getLessonsByCategory(categoryId: String): List<Lesson> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "lessons",
            null,
            "category_id = ?",
            arrayOf(categoryId.toString()),
            null, null, null
        )

        val lessons = mutableListOf<Lesson>()
        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
            lessons.add(Lesson(id, categoryId, name, content))
        }
        cursor.close()
        db.close()
        return lessons
    }

    fun getLessonById(id: String): Lesson? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "lessons",
            arrayOf("id", "category_id", "name", "content"),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null,
            "1"
        )

        val lesson = cursor.use {
            if (it.moveToFirst()) {
                Lesson(
                    id = it.getString(it.getColumnIndexOrThrow("id")),
                    categoryId = it.getString(it.getColumnIndexOrThrow("category_id")),
                    name = it.getString(it.getColumnIndexOrThrow("name")),
                    content = it.getString(it.getColumnIndexOrThrow("content")),
                )
            } else null
        }

        return lesson
    }

}
