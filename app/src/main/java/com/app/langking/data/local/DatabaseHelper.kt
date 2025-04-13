package com.app.langking.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.langking.data.model.Message
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
        db.execSQL(CREATE_TABLE_MESSAGE)
        db.execSQL(CREATE_TABLE_CONVERSATIONS)
        db.execSQL(CREATE_TABLE_CONVERSATION_MESSAGE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${AccountDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${UserProfileDAO.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS categories")
        db.execSQL("DROP TABLE IF EXISTS lessons")
        db.execSQL("DROP TABLE IF EXISTS words")
        db.execSQL("DROP TABLE IF EXISTS user_progress")
        db.execSQL("DROP TABLE IF EXISTS messages")
        db.execSQL("DROP TABLE IF EXISTS conversations")
        db.execSQL("DROP TABLE IF EXISTS conversation_messages")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "langking.db"
        private const val DATABASE_VERSION = 2

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
                content TEXT NOT NULL,
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
                image_url TEXT,
                description TEXT,
                description_vietnamese TEXT,
                FOREIGN KEY (lesson_id) REFERENCES lessons(id)
            );
        """


        private const val CREATE_TABLE_USER_PROGRESS = """
            CREATE TABLE user_progress (
                user_id INTEGER NOT NULL,
                lesson_id INTEGER NOT NULL,
                progress INTEGER DEFAULT 0,
                date_test TEXT DEFAULT '',
                date_start TEXT DEFAULT '',
                PRIMARY KEY (user_id, lesson_id),
                FOREIGN KEY (user_id) REFERENCES ${AccountDAO.TABLE_NAME}(${AccountDAO.COLUMN_ID}),
                FOREIGN KEY (lesson_id) REFERENCES lessons(id)
            );
        """

        private const val CREATE_TABLE_MESSAGE = """
            CREATE TABLE messages (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                sender TEXT CHECK(sender IN ('${Message.SENDER_USER}', '${Message.SENDER_BOT}')) NOT NULL,
                message TEXT NOT NULL,
                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
            );
        """
        private const val CREATE_TABLE_CONVERSATIONS= """
            CREATE TABLE conversations (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                topic TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
            );
        """
        private const val CREATE_TABLE_CONVERSATION_MESSAGE = """
            CREATE TABLE conversation_messages (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                conversation_id INTEGER NOT NULL,
                message_id INTEGER NOT NULL,
                FOREIGN KEY (conversation_id) REFERENCES conversations(id) ON DELETE CASCADE,
                FOREIGN KEY (message_id) REFERENCES messages(id) ON DELETE CASCADE
            );
        """


    }
}