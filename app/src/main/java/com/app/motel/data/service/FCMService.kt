package com.app.motel.data.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.motel.data.model.AppNotification
import com.app.motel.feature.home.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.history.vietnam.R
import com.history.vietnam.ultis.AppConstants


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val title = remoteMessage.notification?.title ?: "Thông báo"
        val body = remoteMessage.notification?.body ?: "Message"
        val payload = Gson().toJson(mapToAppNotification(remoteMessage.data))
        showNotification(title, body, payload)
    }

    private fun showNotification(title: String, body: String, payload: String) {
        val notifyManager = NotificationManagerCompat.from(applicationContext)

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(MainActivity.notificationPayload, payload)
        }

        val pendingIntent = TaskStackBuilder.create(this).run {
            addParentStack(MainActivity::class.java)
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(applicationContext, AppConstants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.icon_app)
            .setColor(resources.getColor(R.color.black, applicationContext.theme))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notifyManager.notify(System.currentTimeMillis().toInt(), notification.build())

        }
    }

    companion object{
        fun mapToAppNotification(inputMap: Map<String, Any?>): AppNotification? {
            val gson = Gson()

            val rawData = inputMap["data"] as? String
            val dataMap: Map<String, String?>? = rawData?.let {
                gson.fromJson(it, object : TypeToken<Map<String, String?>>() {}.type)
            }

            return inputMap["id"]?.let{AppNotification(
                id = it as? String,
                title = inputMap["title"] as? String,
                message = inputMap["message"] as? String,
                type = (inputMap["type"] as? String)?.let { AppNotification.Type.valueOf(it) },
                senderId = inputMap["senderId"] as? String,
                receiverId = inputMap["receiverId"] as? String,
                time = inputMap["time"] as? String,
                read = inputMap["read"] as? Boolean,
                data = dataMap
            )
            }
        }
    }
}
