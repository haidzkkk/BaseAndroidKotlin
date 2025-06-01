package com.app.langking.data.model

data class Conversation(
    val id: String? = null,
    val userId: String,
    val topic: String?,
    val createdAt: String? = null
)
