package com.app.langking.data.repository

import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import com.app.langking.data.network.FirebaseManager
import com.app.langking.ultis.AppConstants
import com.app.langking.ultis.Resource
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val firebaseManager: FirebaseManager
) {

    suspend fun getLessonById(categoryId: String?, lessonId: String?, userId: String): Resource<Lesson> {
        if (categoryId.isNullOrEmpty() || lessonId.isNullOrEmpty()) {
            return Resource.Error(null,"Invalid category or lesson ID")
        }

        val lesson = firebaseManager.getObject("${AppConstants.FIREBASE_CATEGORY_NODE}/${categoryId}/${AppConstants.FIREBASE_LESSON_NODE}/${lessonId}", Lesson::class.java)
        lesson.data?.userProgress = firebaseManager.getObject("${AppConstants.FIREBASE_USER_NODE}/${userId}/${AppConstants.FIREBASE_USER_PROGRESS_NODE}/${lessonId}", UserProgress::class.java).data
        lesson.data?.words = firebaseManager.getList("${AppConstants.FIREBASE_CATEGORY_NODE}/${categoryId}/${AppConstants.FIREBASE_LESSON_NODE}/${lessonId}/${AppConstants.FIREBASE_WORD_NODE}", Word::class.java).data
        return lesson
    }

}