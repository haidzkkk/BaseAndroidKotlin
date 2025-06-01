package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import com.app.langking.data.model.Message

class MessageDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertMessage(message: Message): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", message.id)
            put("user_id", message.userId)
            put("sender", message.sender)
            put("message", message.message)
            put("timestamp", message.timestamp)
        }
        return db.insert("messages", null, values)
    }

    fun getMessagesByUser(userId: String): List<Message> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("messages", null, "user_id = ?", arrayOf(userId.toString()), null, null, "timestamp ASC")
        val messages = mutableListOf<Message>()

        cursor.use {
            while (it.moveToNext()) {
                messages.add(
                    Message(
                        id = it.getString(it.getColumnIndexOrThrow("id")),
                        userId = it.getString(it.getColumnIndexOrThrow("user_id")),
                        sender = it.getString(it.getColumnIndexOrThrow("sender")),
                        message = it.getString(it.getColumnIndexOrThrow("message")),
                        timestamp = it.getString(it.getColumnIndexOrThrow("timestamp"))
                    )
                )
            }
        }
        return messages
    }

    fun deleteMessagesByUser(userId: String): Int {
        val db = dbHelper.writableDatabase
        return db.delete("messages", "user_id = ?", arrayOf(userId.toString()))
    }
}

