package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import com.app.langking.data.model.Account
import com.google.gson.Gson
import javax.inject.Inject

class AccountDAO @Inject constructor(val context: Context) {
    private val dbHelper = DatabaseHelper(context)

    companion object {
        const val TABLE_NAME = "account"
        const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_FULL_NAME = "full_name"
        private const val COLUMN_AVATAR = "avatar"
        private const val COLUMN_CREATED_AT = "created_at"

        const val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT UNIQUE NOT NULL,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_FULL_NAME TEXT,
                $COLUMN_AVATAR TEXT,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """
    }

    fun insertAccount(account: Account): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, account.username)
            put(COLUMN_EMAIL, account.email)
            put(COLUMN_PASSWORD, account.password)
            put(COLUMN_FULL_NAME, account.fullName)
            put(COLUMN_AVATAR, account.avatar)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAccountById(id: Int): Account? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_ID = ?", arrayOf(id.toString()), null, null, null)

        return if (cursor.moveToFirst()) {
            val account = Account(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)),
                avatar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR))
            )
            cursor.close()
            account
        } else {
            cursor.close()
            null
        }
    }

    fun updateAccount(account: Account): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, account.username)
            put(COLUMN_EMAIL, account.email)
            put(COLUMN_FULL_NAME, account.fullName)
            put(COLUMN_AVATAR, account.avatar)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(account.id.toString()))
    }

    fun deleteAccount(id: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getAccountByUsername(username: String): Account? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_USERNAME = ?", arrayOf(username), null, null, null)

        return if (cursor.moveToFirst()) {
            val account = Account(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)),
                avatar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR))
            )
            cursor.close()
            account
        } else {
            cursor.close()
            null
        }
    }

    fun getAccountByEmail(email: String): Account? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TABLE_NAME, null, "$COLUMN_EMAIL = ?", arrayOf(email), null, null, null)

        return if (cursor.moveToFirst()) {
            val account = Account(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD)),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME)),
                avatar = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR))
            )
            cursor.close()
            account
        } else {
            cursor.close()
            null
        }
    }

    fun saveCurrentAccount(account: Account) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val accountJson = Gson().toJson(account)
        editor.putString("account", accountJson)
        editor.apply()
    }

    fun getCurrentAccount(): Account? {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val accountJson = sharedPreferences.getString("account", null)
        return if (accountJson != null) Gson().fromJson(accountJson, Account::class.java) else null
    }
}
