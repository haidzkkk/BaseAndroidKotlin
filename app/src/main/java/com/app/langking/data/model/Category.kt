package com.app.langking.data.model

data class Category(
    val id: Int = 0,
    val name: String,

    val lessons: List<Lesson> = arrayListOf()
)
