package com.app.langking

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import com.app.langking.di.DaggerTravleComponent
import com.app.langking.di.TravleComponent
import com.app.langking.feature.daily.DailyAlarmManager
import com.app.langking.feature.notification.AppNotificationManager
import com.google.firebase.FirebaseApp

class TravleApplication : Application() {

    val travleComponent: TravleComponent by lazy {
        DaggerTravleComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        travleComponent.inject(this)
        FirebaseApp.initializeApp(this)

        DailyAlarmManager.scheduleAlarm(this)
        AppNotificationManager.createDailyNotificationChannel(getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

    }
}