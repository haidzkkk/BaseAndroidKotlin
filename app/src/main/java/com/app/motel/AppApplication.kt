package com.history.vietnam

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.app.motel.data.service.FirebaseAccessToken
import com.google.firebase.FirebaseApp
import com.history.vietnam.di.DaggerAppComponent
import com.history.vietnam.di.AppComponent
import com.history.vietnam.ultis.AppConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        FirebaseApp.initializeApp(this)

        CoroutineScope(Dispatchers.IO).launch {
            FirebaseAccessToken.getFireBaseAccessToken(applicationContext)
        }
        setupNotifyChannel()
    }

    private fun setupNotifyChannel() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val channelAll = NotificationChannel(AppConstants.NOTIFICATION_CHANNEL_ID, "Thông báo tổng", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channelAll)
        }
    }
}