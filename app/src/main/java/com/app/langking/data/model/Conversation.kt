package com.app.langking.data.model

data class Conversation(
    val id: Int? = null,
    val userId: Int,
    val topic: String?,
    val createdAt: String? = null
)
