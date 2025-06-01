package com.app.langking.data.model

import com.app.langking.ultis.IDManager

data class Message(
    val id: String? = IDManager.createID(),
    val userId: String,
    val sender: String,
    val message: String,
    val timestamp: String? = null
){
    val isUser: Boolean
        get () = sender == "user"

    val isBot: Boolean
        get () = sender == "model"

    companion object{
        const val SENDER_USER = "user"
        const val SENDER_BOT = "model"
    }
}
