package com.app.langking.data.model

data class Question(
    val title: String,
    val options: List<String>,
    val correctAnswer: String
)
