package com.app.langking.data.local
import android.content.Context
import android.util.Log
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.Message
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import com.app.langking.ultis.DateConverter
import javax.inject.Inject

class DatabaseManager @Inject constructor(val context: Context) {
    init {
        if(getUserCategory().isEmpty()){
            insertData()
        }
    }

    private fun insertData(){
        val categoryDAO = CategoryDao(context)
        val lessonDao = LessonDao(context)
        val wordDao = WordDao(context)
        val userProgressDao = UserProgressDao(context)

        DatabaseData.getSampleCategories().forEach { category -> categoryDAO.insertCategory(category) }
        DatabaseData.getSampleLesson().forEach { lesson -> lessonDao.insertLesson(lesson) }
        DatabaseData.getSampleWords().forEach { word -> wordDao.insertWord(word) }
        DatabaseData.getSampleUserProgress().forEach { userProgress -> userProgressDao.updateProgress(userProgress) }
    }

    fun addUserProgress(userProgress: UserProgress){
        val userProgressDao = UserProgressDao(context)
        userProgressDao.updateProgress(userProgress)
    }

    fun addMessage(message: Message): Message? {
        val messageDao = MessageDao(context)
        val messageId: Int = messageDao.insertMessage(message).toInt()
        if(messageId == -1) return null
        return message.copy(id = messageId)
    }

    /// get
    fun getMessageByUserId(userId: Int): List<Message>{
        val messageDao = MessageDao(context)
        val messages = messageDao.getMessagesByUser(userId)
        return messages;
    }

    fun removeMessageByUserId(userId: Int){
        val messageDao = MessageDao(context)
        val messages = messageDao.deleteMessagesByUser(userId)
    }

    fun getUserCategory(): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getCategories()
        return categories;
    }
    fun getUserLesson(): List<Lesson>{
        val lessonDao = LessonDao(context)
        val lessons = lessonDao.getLessonsByCategory(0)
        return lessons
    }

    fun getLessonById(lessonId: Int): Lesson?{
        val lessonDao = LessonDao(context)
        val lesson = lessonDao.getLessonById(lessonId)
        return lesson
    }

    fun getLessonDetailById(lessonId: Int, userId: Int): Lesson?{
        val lessonDao = LessonDao(context)
        val userProgressDao = UserProgressDao(context)
        val lesson: Lesson? = lessonDao.getLessonById(lessonId)
        lesson?.words = WordDao(context).getWordsByLesson(lessonId)
        lesson?.userProgress = userProgressDao.getUserProgress(userId, lessonId) ?: UserProgress(userId = userId, lessonId = lessonId)
        return lesson
    }

    fun getUserWord(): List<Word>{
        val wordDao = WordDao(context)
        val words = wordDao.getWordsByLesson(0)
        return words;
    }

    fun getUserProgress(userId: Int): List<UserProgress>{
        val userProgressDao = UserProgressDao(context)
        val userProgresses = userProgressDao.getUserProgress(userId)
        return userProgresses
    }

    fun getUserProgress(userId: Int, lessonId: Int): UserProgress?{
        val userProgressDao = UserProgressDao(context)
        val userProgress = userProgressDao.getUserProgress(userId, lessonId)
        return userProgress
    }

    fun getAllCategoriesWithLessonsAndWords(userId: Int): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getAllCategoriesDetail()
        categories.forEach {
            it.lessons?.forEach { lesson ->
                lesson.userProgress = getUserProgress(userId, lesson.id)
            }
        }
        Log.e("getAllCategoriesWithLessonsAndWords", categories.toString())
        return categories
    }

}