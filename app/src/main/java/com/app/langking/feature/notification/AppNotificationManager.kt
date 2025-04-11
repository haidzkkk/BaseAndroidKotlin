package com.app.langking.feature.notification

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.langking.R
import com.app.langking.ultis.AppConstants
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.app.langking.data.model.Lesson
import com.app.langking.feature.MainActivity

object AppNotificationManager {

    fun createDailyNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AppConstants.NOTIFICATION_CHANNEL_ID,
                AppConstants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = AppConstants.NOTIFICATION_CHANNEL_DESCRIPTION
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showDailyNotification(context: Context, lesson: Lesson?) {
        val avatarBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.imoji_avatar)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            lesson?.let {
                putExtra("lesson_id", it.id)
            }
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val builder = NotificationCompat.Builder(context, AppConstants.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app)
            .setLargeIcon(avatarBitmap)
            .setContentTitle("Hi! It's Lang")
            .setContentText(
                "Looks like you missed your ${lesson?.name ?: "English"} lesson again. Good luck " +
                        "talking your way out of this one!"
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1001, builder.build())
        }
    }
}