package com.app.langking.data.model

data class Message(
    val id: Int? = null,
    val userId: Int,
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
