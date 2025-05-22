package com.app.motel.data.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.app.motel.data.model.AppNotification
import com.app.motel.data.model.FirebaseNotificationBody
import com.app.motel.data.model.Notification
import com.app.motel.data.model.PageInfo
import com.app.motel.data.network.ApiFirebase
import com.app.motel.data.network.FirebaseManager
import com.app.motel.data.service.FirebaseAccessToken
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.ultis.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val apiFirebase: ApiFirebase,
    private val userRepository: UserRepository,
    private val firebaseManager: FirebaseManager,
) {

    fun addListenerNotification(userId: String, listener: ValueEventListener) =
        firebaseManager.addListener("${AppConstants.FIREBASE_USER_PATH}/${userId}/${AppConstants.FIREBASE_NOTIFICATION_NODE}", listener)

    fun removeNotification(userId: String, listener: ValueEventListener) =
        firebaseManager.removeListener("${AppConstants.FIREBASE_USER_PATH}/${userId}/${AppConstants.FIREBASE_NOTIFICATION_NODE}", listener)

    suspend fun getNotifications(userId: String): List<AppNotification>? {
        val path = "${AppConstants.FIREBASE_USER_PATH}/${userId}/${AppConstants.FIREBASE_NOTIFICATION_NODE}"
        return firebaseManager.getList(path, AppNotification::class.java).data
    }

    suspend fun addNotification(notification: AppNotification){
        if(notification.id.isNullOrEmpty() || notification.receiverId.isNullOrEmpty()) return
        val path = "${AppConstants.FIREBASE_USER_PATH}/${notification.receiverId}/${AppConstants.FIREBASE_NOTIFICATION_NODE}/${notification.id}"
        firebaseManager.push(path, notification)
    }

    suspend fun sendNotification(notification: AppNotification) {
        if(notification.receiverId.isNullOrEmpty()) return
        val receiver = userRepository.getUserById(notification.receiverId)
        if(receiver == null || receiver.tokenDevice.isNullOrEmpty()) return
        val token = receiver.tokenDevice

        val body: FirebaseNotificationBody = FirebaseNotificationBody.createBody(
            deviceToken = token,
            title = notification.title ?: "Thông báo",
            body = notification.message ?: "Nội dung",
            notification = notification
        )
        apiFirebase.postNotification(body = body)
    }

    suspend fun updateNotification(notification: AppNotification){
        val path = "${AppConstants.FIREBASE_USER_PATH}/${notification.receiverId}/${AppConstants.FIREBASE_NOTIFICATION_NODE}/${notification.id}"
        firebaseManager.overwrite(path, notification)
    }
}