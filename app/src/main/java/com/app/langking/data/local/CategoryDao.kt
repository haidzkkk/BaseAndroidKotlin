package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.Word

class CategoryDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertCategory(category: Category): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", category.name)
        }
        val id = db.insert("categories", null, values)
        db.close()
        return id
    }

    fun getCategories(): List<Category> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor: Cursor = db.query("categories", null, null, null, null, null, null)

        val categories = mutableListOf<Category>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            categories.add(Category(id, name))
        }
        cursor.close()
        db.close()
        return categories
    }


    fun getAllCategoriesWithLessonsAndWords(): List<Category> {
        val db = dbHelper.readableDatabase
        val categoriesList = mutableListOf<Category>()

        // Lấy tất cả các Category
        val categoryCursor = db.rawQuery("SELECT * FROM categories", null)

        categoryCursor.use { catCursor ->
            while (catCursor.moveToNext()) {
                val categoryId = catCursor.getInt(catCursor.getColumnIndexOrThrow("id"))
                val categoryName = catCursor.getString(catCursor.getColumnIndexOrThrow("name"))

                // Lấy tất cả các bài học trong Category
                val lessonCursor = db.rawQuery("SELECT * FROM lessons WHERE category_id = ?", arrayOf(categoryId.toString()))
                val lessonList = mutableListOf<Lesson>()

                lessonCursor.use { lessCursor ->
                    while (lessCursor.moveToNext()) {
                        val lessonId = lessCursor.getInt(lessCursor.getColumnIndexOrThrow("id"))
                        val lessonName = lessCursor.getString(lessCursor.getColumnIndexOrThrow("name"))

                        // Lấy tất cả các từ vựng trong Lesson
                        val wordCursor = db.rawQuery("SELECT * FROM words WHERE lesson_id = ?", arrayOf(lessonId.toString()))
                        val wordList = mutableListOf<Word>()

                        wordCursor.use { wCursor ->
                            while (wCursor.moveToNext()) {
                                val wordId = wCursor.getInt(wCursor.getColumnIndexOrThrow("id"))
                                val english = wCursor.getString(wCursor.getColumnIndexOrThrow("english"))
                                val vietnamese = wCursor.getString(wCursor.getColumnIndexOrThrow("vietnamese"))
                                val pronunciation = wCursor.getString(wCursor.getColumnIndexOrThrow("pronunciation"))
                                val audioUrl = wCursor.getString(wCursor.getColumnIndexOrThrow("audio_url"))

                                wordList.add(Word(wordId, lessonId, english, vietnamese, pronunciation, audioUrl))
                            }
                        }

                        lessonList.add(Lesson(lessonId, categoryId, lessonName, wordList))
                    }
                }

                categoriesList.add(Category(categoryId, categoryName, lessonList))
            }
        }

        db.close()
        return categoriesList
    }
}
