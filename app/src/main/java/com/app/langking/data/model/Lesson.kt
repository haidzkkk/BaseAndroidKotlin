package com.app.langking.data.model

data class Lesson(
    val id: Int = 0,
    val categoryId: Int,
    val name: String,

    val words: List<Word> = arrayListOf(),
)
