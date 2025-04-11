package com.app.langking

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.app.langking.di.DaggerTravleComponent
import com.app.langking.di.TravleComponent
import com.app.langking.feature.daily.DailyAlarmManager
import com.app.langking.feature.notification.AppNotificationManager
import com.app.langking.ultis.AppConstants

class TravleApplication : Application() {

    val travleComponent: TravleComponent by lazy {
        DaggerTravleComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        travleComponent.inject(this)

        DailyAlarmManager.scheduleAlarm(this)
        AppNotificationManager.createDailyNotificationChannel(getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

    }
}