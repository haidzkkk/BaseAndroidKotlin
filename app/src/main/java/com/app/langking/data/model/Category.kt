package com.app.langking.data.model

import com.app.langking.ultis.IDManager
import com.google.firebase.database.Exclude

data class Category(
    override val id: String = IDManager.createID(),
    val name: String? = null,

    @get:Exclude
    var lessons: List<Lesson>? = null,
    val position: Int? = null,
): RealTimeId {

    val isComplete: Boolean
        get() = lessons?.let {
            it.forEach { lesson ->
                if(lesson.userProgress?.isComplete != true) return false
            }
            true
        } ?: false

    fun checkCompletedCategoryPrevious(previousCategory: Category?): Boolean{
        if((position ?: 0) <= 1) return true
        return previousCategory?.position == ((position ?: 0) - 1) && previousCategory.isComplete
    }

    fun lessonWithoutProcess(): List<Lesson>?{
        return lessons?.filter { lesson ->
            lesson.userProgress == null
        }
    }
}
