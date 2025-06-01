package com.app.langking.data.repository


import android.util.Log
import com.app.langking.data.local.DatabaseData
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Account
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import com.app.langking.data.network.ApiTravle
import com.app.langking.data.network.FirebaseManager
import com.app.langking.ultis.AppConstants
import com.app.langking.ultis.Resource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiTravle,
    private val firebaseManager: FirebaseManager,
    private val dbManager: DatabaseManager,
) {

    suspend fun importSampleData(){

        val categories: List<Category> = DatabaseData.getSampleCategories().map { it.copy(lessons = null) }
        val lessons: List<Lesson> = DatabaseData.getSampleLesson().map { it.copy(words = null) }
        val words: List<Word> = DatabaseData.getSampleWords()
        val users = DatabaseData.getSampleUser()

        firebaseManager.push(AppConstants.FIREBASE_USER_NODE ,users)
        firebaseManager.push(AppConstants.FIREBASE_CATEGORY_NODE ,categories)
        categories.forEachIndexed { indexCategory, category ->
            val lessonOfCategory = lessons.filter { it.categoryId == category.id }.mapIndexed { indexLesson, lesson ->
                lesson.copy(position = indexLesson + 1)
            }.toList()
            firebaseManager.push("${AppConstants.FIREBASE_CATEGORY_NODE}/${category.id}/${AppConstants.FIREBASE_LESSON_NODE}" ,lessonOfCategory)

            lessonOfCategory.forEach { lesson ->
                val wordOfLesson = words.filter { it.lessonId == lesson.id }.mapIndexed { indexWord, word ->
                    word.copy(position = indexWord + 1)
                }.toList()
                firebaseManager.push("${AppConstants.FIREBASE_CATEGORY_NODE}/${category.id}/${AppConstants.FIREBASE_LESSON_NODE}/${lesson.id}/${AppConstants.FIREBASE_WORD_NODE}" ,wordOfLesson)
            }
        }

    }

    suspend fun getSampleData(): Resource<List<Category>>{
        return firebaseManager.getList(AppConstants.FIREBASE_CATEGORY_NODE, Category::class.java)
    }

    suspend fun getCategoriesByUser(): Resource<List<Category>>{
        return firebaseManager.getList(AppConstants.FIREBASE_CATEGORY_NODE, Category::class.java)
    }

    suspend fun getLessonProcessById(categoryId: String, userId: String): List<Lesson>?{
        val lessons = firebaseManager.getList("${AppConstants.FIREBASE_CATEGORY_NODE}/${categoryId}/${AppConstants.FIREBASE_LESSON_NODE}", Lesson::class.java).data
        lessons?.forEach { lesson ->
            if(userId == Account.ACCOUNT_DEFAULT_ID){
                lesson.userProgress = dbManager.getUserProgress(userId, lesson.id)
            }else{
                val data = firebaseManager.getObject("${AppConstants.FIREBASE_USER_NODE}/${userId}/${AppConstants.FIREBASE_USER_PROGRESS_NODE}/${lesson.id}", UserProgress::class.java)
                lesson.userProgress = data.data
            }
        }
        return lessons
    }


    suspend fun pushUserProgress(userProgress: UserProgress){
        if(userProgress.userId == Account.ACCOUNT_DEFAULT_ID){
            dbManager.addUserProgress(userProgress)
        }else{
            firebaseManager.push("${AppConstants.FIREBASE_USER_NODE}/${userProgress.userId}/${AppConstants.FIREBASE_USER_PROGRESS_NODE}/${userProgress.lessonId}", userProgress)

        }
    }

}