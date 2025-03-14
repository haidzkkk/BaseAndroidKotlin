package com.app.langking.data.model

data class Lesson(
    val id: Int = 0,
    val categoryId: Int,
    val name: String,

    var words: List<Word>? = null,
    var userProgress: UserProgress? = null,
)
