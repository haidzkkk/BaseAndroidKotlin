package com.app.langking.data.local
import android.content.Context
import com.app.langking.data.model.Category
import com.app.langking.data.model.Lesson
import com.app.langking.data.model.UserProgress
import com.app.langking.data.model.Word
import javax.inject.Inject

class DatabaseManager @Inject constructor(val context: Context) {
    fun insertSampleCategories() {
        val categoryDAO = CategoryDao(context)

        val categories = listOf(
            Category(id = 1, name = "Basic Vocabulary"),
            Category(id = 2, name = "Travel & Tourism"),
            Category(id = 3, name = "Business English"),
            Category(id = 4, name = "Everyday Conversations"),
            Category(id = 5, name = "English for Kids")
        )

        for (category in categories) {
            categoryDAO.insertCategory(category)
        }
    }

    fun insertSampleLesson() {
        val lessonDao = LessonDao(context)

        val lessons = listOf(
            // Basic Vocabulary
            Lesson(id = 1, categoryId = 1, name = "Common Words"),
            Lesson(id = 2, categoryId = 1, name = "Numbers & Colors"),
            Lesson(id = 3, categoryId = 1, name = "Basic Phrases"),

            // Travel & Tourism
            Lesson(id = 4, categoryId = 2, name = "At the Airport"),
            Lesson(id = 5, categoryId = 2, name = "Hotel Conversations"),
            Lesson(id = 6, categoryId = 2, name = "Ordering Food"),

            // Business English
            Lesson(id = 7, categoryId = 3, name = "Office Communication"),
            Lesson(id = 8, categoryId = 3, name = "Email Writing"),
            Lesson(id = 9, categoryId = 3, name = "Job Interviews"),

            // Everyday Conversations
            Lesson(id = 10, categoryId = 4, name = "Shopping Dialogues"),
            Lesson(id = 11, categoryId = 4, name = "Making Appointments"),
            Lesson(id = 12, categoryId = 4, name = "Small Talk"),

            // English for Kids
            Lesson(id = 13, categoryId = 5, name = "Animal Names"),
            Lesson(id = 14, categoryId = 5, name = "Fun with Rhymes"),
            Lesson(id = 15, categoryId = 5, name = "Storytelling")
        )

        lessons.forEach { lesson ->
            lessonDao.insertLesson(lesson)
        }
    }

    fun insertSampleWords() {
        val wordDao = WordDao(context)

        val words = listOf(
            // Common Words
            Word(lessonId = 1, english = "Hello", vietnamese = "Xin chào", pronunciation = "həˈloʊ", audioUrl = ""),
            Word(lessonId = 1, english = "Goodbye", vietnamese = "Tạm biệt", pronunciation = "ˌɡʊdˈbaɪ", audioUrl = ""),
            Word(lessonId = 1, english = "Please", vietnamese = "Làm ơn", pronunciation = "pliːz", audioUrl = ""),
            Word(lessonId = 1, english = "Thank you", vietnamese = "Cảm ơn", pronunciation = "ˈθæŋk ju", audioUrl = ""),
            Word(lessonId = 1, english = "Yes", vietnamese = "Vâng", pronunciation = "jɛs", audioUrl = ""),
            Word(lessonId = 1, english = "No", vietnamese = "Không", pronunciation = "noʊ", audioUrl = ""),
            Word(lessonId = 1, english = "Excuse me", vietnamese = "Xin lỗi", pronunciation = "ɪkˈskjuːz mi", audioUrl = ""),
            Word(lessonId = 1, english = "Sorry", vietnamese = "Xin lỗi", pronunciation = "ˈsɑːri", audioUrl = ""),
            Word(lessonId = 1, english = "Help", vietnamese = "Giúp đỡ", pronunciation = "hɛlp", audioUrl = ""),
            Word(lessonId = 1, english = "Friend", vietnamese = "Bạn bè", pronunciation = "frɛnd", audioUrl = ""),

            // Numbers & Colors
            Word(lessonId = 2, english = "One", vietnamese = "Một", pronunciation = "wʌn", audioUrl = ""),
            Word(lessonId = 2, english = "Two", vietnamese = "Hai", pronunciation = "tuː", audioUrl = ""),
            Word(lessonId = 2, english = "Three", vietnamese = "Ba", pronunciation = "θriː", audioUrl = ""),
            Word(lessonId = 2, english = "Four", vietnamese = "Bốn", pronunciation = "fɔːr", audioUrl = ""),
            Word(lessonId = 2, english = "Five", vietnamese = "Năm", pronunciation = "faɪv", audioUrl = ""),
            Word(lessonId = 2, english = "Red", vietnamese = "Màu đỏ", pronunciation = "rɛd", audioUrl = ""),
            Word(lessonId = 2, english = "Blue", vietnamese = "Màu xanh dương", pronunciation = "bluː", audioUrl = ""),
            Word(lessonId = 2, english = "Green", vietnamese = "Màu xanh lá", pronunciation = "ɡriːn", audioUrl = ""),
            Word(lessonId = 2, english = "Yellow", vietnamese = "Màu vàng", pronunciation = "ˈjɛloʊ", audioUrl = ""),
            Word(lessonId = 2, english = "Black", vietnamese = "Màu đen", pronunciation = "blæk", audioUrl = ""),

            // Basic Phrases
            Word(lessonId = 3, english = "How are you?", vietnamese = "Bạn khỏe không?", pronunciation = "haʊ ɑr ju?", audioUrl = ""),
            Word(lessonId = 3, english = "What’s your name?", vietnamese = "Bạn tên gì?", pronunciation = "wɑːts jɔːr neɪm?", audioUrl = ""),
            Word(lessonId = 3, english = "Where are you from?", vietnamese = "Bạn đến từ đâu?", pronunciation = "wɛr ɑr ju frʌm?", audioUrl = ""),
            Word(lessonId = 3, english = "I don’t understand", vietnamese = "Tôi không hiểu", pronunciation = "aɪ doʊnt ˌʌndərˈstænd", audioUrl = ""),
            Word(lessonId = 3, english = "Can you help me?", vietnamese = "Bạn có thể giúp tôi không?", pronunciation = "kæn ju hɛlp mi?", audioUrl = ""),
            Word(lessonId = 3, english = "I am lost", vietnamese = "Tôi bị lạc", pronunciation = "aɪ æm lɔst", audioUrl = ""),
            Word(lessonId = 3, english = "I am sorry", vietnamese = "Tôi xin lỗi", pronunciation = "aɪ æm ˈsɑːri", audioUrl = ""),
            Word(lessonId = 3, english = "Good morning", vietnamese = "Chào buổi sáng", pronunciation = "ɡʊd ˈmɔrnɪŋ", audioUrl = ""),
            Word(lessonId = 3, english = "Good night", vietnamese = "Chúc ngủ ngon", pronunciation = "ɡʊd naɪt", audioUrl = ""),
            Word(lessonId = 3, english = "See you later", vietnamese = "Hẹn gặp lại", pronunciation = "si ju ˈleɪtər", audioUrl = ""),
        )

        words.forEach { word ->
            wordDao.insertWord(word)
        }
    }


    fun insertSampleUserProgress() {
        val userProgressDao = UserProgressDao(context)

        val userProgresses = listOf(
            UserProgress(userId = 0, lessonId = 0, progress = 30),
        )

        userProgresses.forEach { userProgress ->
            userProgressDao.updateProgress(userProgress)
        }

    }


    /// get
    fun getUserCategory(): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getCategories()
        return categories;
    }
    fun getUserLesson(): List<Lesson>{
        val lessonDao = LessonDao(context)
        val lessons = lessonDao.getLessonsByCategory(0)
        return lessons;
    }
    fun getUserWord(): List<Word>{
        val wordDao = WordDao(context)
        val words = wordDao.getWordsByLesson(0)
        return words;
    }

    fun getUserProgress(): List<UserProgress>{
        val userProgressDao = UserProgressDao(context)
        val userProgress = userProgressDao.getUserProgress(0,0)
        return listOf(userProgress!!);
    }

    fun getAllCategoriesWithLessonsAndWords(): List<Category>{
        val categoryDao = CategoryDao(context)
        val categories = categoryDao.getAllCategoriesWithLessonsAndWords()
        return categories
    }

}