package com.app.motel.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.history.vietnam.ultis.serializeToMap

data class FirebaseNotificationBody(
    val message: Message,
){
    companion object{
        fun createBody(
            deviceToken: String,
            title: String,
            body: String,
            payload: Map<String, String>?,
        ): FirebaseNotificationBody{
            return FirebaseNotificationBody(
                message = Message(
                    token = deviceToken,
                    data = payload,
                    notification = Notification(
                        title = title,
                        body = body
                    ),
                    android = Android(
                        notification = NotificationAndroid(
                            icon = "icon_app"
                        )
                    )
                )
            )
        }
        fun createBody(
            deviceToken: String,
            title: String,
            body: String,
            notification: AppNotification?,
        ): FirebaseNotificationBody{
            return FirebaseNotificationBody(
                message = Message(
                    token = deviceToken,
                    data = notification?.serializeToMap(),
                    notification = Notification(
                        title = title,
                        body = body
                    ),
                    android = Android(
                        notification = NotificationAndroid(
                            icon = "icon_app"
                        )
                    )
                )
            )
        }
    }
}

data class Message(
    val token: String,
    val notification: Notification,
    val data: Map<String, String>? = null,
    val android: Android,
)

data class Notification(
    val title: String,
    val body: String,
)

data class Android(
    val notification: NotificationAndroid,
)

data class NotificationAndroid(
    val icon: String = "icon_app",
)
