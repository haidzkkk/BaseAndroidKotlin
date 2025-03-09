package com.app.langking.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject

class DatabaseHelper @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(AccountDAO.CREATE_TABLE)
        db.execSQL(UserProfileDAO.CREATE_TABLE)
        db.execSQL(CREATE_TABLE_CATEGORIES)
        db.execSQL(CREATE_TABLE_LESSONS)
        db.execSQL(CREATE_TABLE_WORDS)
        db.execSQL(CREATE_TABLE_USER_PROGRESS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${AccountDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${UserProfileDAO.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "langking.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_CATEGORIES: String = """
            CREATE TABLE categories (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            );
        """


        private const val CREATE_TABLE_LESSONS = """
            CREATE TABLE lessons (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                category_id INTEGER NOT NULL,
                name TEXT NOT NULL,
                FOREIGN KEY (category_id) REFERENCES categories(id)
            );
        """

        private const val CREATE_TABLE_WORDS = """
            CREATE TABLE words (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                lesson_id INTEGER NOT NULL,
                english TEXT NOT NULL,
                vietnamese TEXT NOT NULL,
                pronunciation TEXT,
                audio_url TEXT,
                FOREIGN KEY (lesson_id) REFERENCES lessons(id)
            );
        """

        private const val CREATE_TABLE_USER_PROGRESS = """
            CREATE TABLE user_progress (
                user_id INTEGER NOT NULL,
                lesson_id INTEGER NOT NULL,
                progress INTEGER DEFAULT 0,
                PRIMARY KEY (user_id, lesson_id),
                FOREIGN KEY (user_id) REFERENCES ${AccountDAO.TABLE_NAME}(${AccountDAO.COLUMN_ID}),
                FOREIGN KEY (lesson_id) REFERENCES lessons(id)
            );
        """
    }
}