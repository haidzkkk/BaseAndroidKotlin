package com.app.langking.data.model

data class UserProgress(
    val userId: Int,
    val lessonId: Int,
    val progress: Int = 0
)
