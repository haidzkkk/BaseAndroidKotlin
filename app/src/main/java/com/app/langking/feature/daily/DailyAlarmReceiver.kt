package com.app.langking.feature.daily

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.local.LessonDao
import com.app.langking.data.local.UserProgressDao
import com.app.langking.data.model.Account
import com.app.langking.data.model.Lesson
import com.app.langking.data.network.FirebaseManager
import com.app.langking.data.repository.UserRepository
import com.app.langking.feature.notification.AppNotificationManager
import com.app.langking.ultis.AppConstants
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DailyAlarmReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val lesson = getLessonLearning(context)
            withContext(Dispatchers.Main) {
                AppNotificationManager.showDailyNotification(context, lesson)
            }
        }
    }

    private suspend fun getLessonLearning(context: Context): Lesson?{
        val userRepo = UserRepository(
            sharedPreferences = context.getSharedPreferences(AppConstants.prefsKey, Context.MODE_PRIVATE),
            firebaseManager = FirebaseManager()
        )
        val userProgressDao = UserProgressDao(context)
        val lessonDao = LessonDao(context)

        val listData = userProgressDao.getUserProgress(userRepo.getCurrentUserId())
        val userProgress = listData.firstOrNull { !it.isComplete }
        return lessonDao.getLessonById(userProgress?.lessonId ?: "")
    }
}