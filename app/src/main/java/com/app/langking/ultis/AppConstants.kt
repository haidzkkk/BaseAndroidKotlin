package com.app.langking.ultis

object AppConstants {
    val NOTIFICATION_CHANNEL_ID: String = "DailyNotificationChannel"
    val NOTIFICATION_CHANNEL_NAME: CharSequence = "Daily Notification"
    val NOTIFICATION_CHANNEL_DESCRIPTION: String = "Notification for daily reminders"

    val prefsKey: String = "app_prefs"
    val currentUserKey: String = "currentUser"

    val isEnglish: String = "isEnglish"
    val isEnglishSlow: String = "isEnglishSlow"
    val isVietnamese: String = "isVietnamese"
    val isEnglishDesc: String = "isEnglishDesc"
    val isVietnameseDesc: String = "isVietnameseDesc"

    val isDailyNotify: String = "isDailyNotify"

    const val EXTRA_USER_LOGIN = "user_login"

    const val FIREBASE_ROOT_DB = "LangKingData"
    const val FIREBASE_CATEGORY_NODE = "categories"
    const val FIREBASE_LESSON_NODE = "lessons"
    const val FIREBASE_WORD_NODE = "words"
    const val FIREBASE_USER_NODE = "users"
    const val FIREBASE_USER_PROGRESS_NODE = "userProcesses"

}