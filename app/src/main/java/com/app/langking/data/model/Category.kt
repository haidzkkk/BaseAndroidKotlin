package com.app.langking.data.model

data class Category(
    val id: Int = 0,
    val name: String,

    var lessons: List<Lesson>? = null,
){

    val isComplete: Boolean
        get() = lessons?.let {
            it.forEach { lesson ->
                if(lesson.userProgress?.isComplete != true) return false
            }
            true
        } ?: false

    fun checkCompletedCategoryPrevious(previousCategory: Category?): Boolean{
        if(id == 1) return true
        return previousCategory?.id == (id - 1) && previousCategory.isComplete
    }

    fun getLessonWithoutProcess(): List<Lesson>?{
        return lessons?.filter { lesson ->
            lesson.userProgress == null
        }
    }
}
