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
            put("category_id", lesson.categoryId)
            put("name", lesson.name)
        }
        val id = db.insert("lessons", null, values)
        db.close()
        return id
    }

    fun getLessonsByCategory(categoryId: Int): List<Lesson> {
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
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            lessons.add(Lesson(id, categoryId, name))
        }
        cursor.close()
        db.close()
        return lessons
    }

    fun getLessonById(id: Int): Lesson? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "lessons",
            arrayOf("id", "category_id", "name"), // Chỉ lấy các cột cần thiết
            "id = ?",
            arrayOf(id.toString()),
            null, null, null,
            "1" // Giới hạn kết quả về 1 bản ghi
        )

        val lesson = cursor.use {
            if (it.moveToFirst()) {
                Lesson(
                    id = it.getInt(it.getColumnIndexOrThrow("id")),
                    categoryId = it.getInt(it.getColumnIndexOrThrow("category_id")),
                    name = it.getString(it.getColumnIndexOrThrow("name"))
                )
            } else null
        }

        return lesson
    }

}
