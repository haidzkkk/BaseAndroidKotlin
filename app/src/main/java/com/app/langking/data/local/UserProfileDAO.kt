package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import com.app.langking.data.model.UserProfile
import javax.inject.Inject


class UserProfileDAO @Inject constructor(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    companion object {
        const val TABLE_NAME = "user_profile"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_GENDER = "gender"
        private const val COLUMN_BIRTHDAY = "birthday"
        private const val COLUMN_LANGUAGE_LEVEL = "language_level"

        const val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_USER_ID INTEGER PRIMARY KEY,
                $COLUMN_GENDER TEXT,
                $COLUMN_BIRTHDAY TEXT,
                $COLUMN_LANGUAGE_LEVEL TEXT DEFAULT 'beginner',
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES ${AccountDAO.TABLE_NAME}(${AccountDAO.COLUMN_ID}) ON DELETE CASCADE
            );
        """
    }

    fun insertUserProfile(profile: UserProfile): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USER_ID, profile.userId)
            put(COLUMN_GENDER, profile.gender)
            put(COLUMN_BIRTHDAY, profile.birthday)
            put(COLUMN_LANGUAGE_LEVEL, profile.languageLevel)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getUserProfile(userId: Int): UserProfile? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_USER_ID = ?", arrayOf(userId.toString()), null, null, null)

        return if (cursor.moveToFirst()) {
            val profile = UserProfile(
                userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)),
                gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER)),
                birthday = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIRTHDAY)),
                languageLevel = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LANGUAGE_LEVEL))
            )
            cursor.close()
            profile
        } else {
            cursor.close()
            null
        }
    }

    fun updateUserProfile(profile: UserProfile): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_GENDER, profile.gender)
            put(COLUMN_BIRTHDAY, profile.birthday)
            put(COLUMN_LANGUAGE_LEVEL, profile.languageLevel)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_USER_ID = ?", arrayOf(profile.userId.toString()))
    }

    fun deleteUserProfile(userId: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_USER_ID = ?", arrayOf(userId.toString()))
    }
}
