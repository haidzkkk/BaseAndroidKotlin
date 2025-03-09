package com.app.langking.data.model

data class Word(
    val id: Int = 0,
    val lessonId: Int,
    val english: String,
    val vietnamese: String,
    val pronunciation: String? = null,
    val audioUrl: String? = null
)
