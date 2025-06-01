package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson

class CategoryDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertCategory(category: Category): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", category.id)
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
            val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            categories.add(Category(id, name))
        }
        cursor.close()
        db.close()
        return categories
    }


    fun getAllCategoriesDetail(): List<Category> {
        val db = dbHelper.readableDatabase
        val categoriesList = mutableListOf<Category>()

        val categoryCursor = db.rawQuery("SELECT * FROM categories", null)

        categoryCursor.use { catCursor ->
            while (catCursor.moveToNext()) {
                val categoryId = catCursor.getString(catCursor.getColumnIndexOrThrow("id"))
                val categoryName = catCursor.getString(catCursor.getColumnIndexOrThrow("name"))

                val lessonCursor = db.rawQuery("SELECT * FROM lessons WHERE category_id = ?", arrayOf(categoryId.toString()))
                val lessonList = arrayListOf<Lesson>()

                lessonCursor.use { lessCursor ->
                    while (lessCursor.moveToNext()) {
                        val lessonId = lessCursor.getString(lessCursor.getColumnIndexOrThrow("id"))
                        val lessonName = lessCursor.getString(lessCursor.getColumnIndexOrThrow("name"))
                        lessonList.add(Lesson(lessonId, categoryId, lessonName))
                    }
                }

                categoriesList.add(Category(categoryId, categoryName, lessonList))
            }
        }

        db.close()
        return categoriesList
    }
}
