package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import com.app.langking.data.model.Conversation

class ConversationDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertConversation(conversation: Conversation): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", conversation.id)
            put("user_id", conversation.userId)
            put("topic", conversation.topic)
            put("created_at", conversation.createdAt)
        }
        return db.insert("conversations", null, values)
    }

    fun getConversationsByUser(userId: Int): List<Conversation> {
        val db = dbHelper.readableDatabase
        val cursor = db.query("conversations", null, "user_id = ?", arrayOf(userId.toString()), null, null, "created_at DESC")
        val conversations = mutableListOf<Conversation>()

        cursor.use {
            while (it.moveToNext()) {
                conversations.add(
                    Conversation(
                        id = it.getString(it.getColumnIndexOrThrow("id")),
                        userId = it.getString(it.getColumnIndexOrThrow("user_id")),
                        topic = it.getString(it.getColumnIndexOrThrow("topic")),
                        createdAt = it.getString(it.getColumnIndexOrThrow("created_at"))
                    )
                )
            }
        }
        return conversations
    }
}
