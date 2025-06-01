package com.app.langking.data.local

import android.content.ContentValues
import android.content.Context
import com.app.langking.data.model.ConversationMessage
import com.app.langking.data.model.Message

class ConversationMessageDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertConversationMessage(conversationMessage: ConversationMessage): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {

            put("conversation_id", conversationMessage.conversationId)
            put("message_id", conversationMessage.messageId)
        }
        return db.insert("conversation_messages", null, values)
    }

    fun getMessagesByConversation(conversationId: Int): List<Message> {
        val db = dbHelper.readableDatabase
        val query = """
            SELECT messages.* FROM messages 
            INNER JOIN conversation_messages ON messages.id = conversation_messages.message_id 
            WHERE conversation_messages.conversation_id = ? 
            ORDER BY messages.timestamp ASC
        """
        val cursor = db.rawQuery(query, arrayOf(conversationId.toString()))
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
}
