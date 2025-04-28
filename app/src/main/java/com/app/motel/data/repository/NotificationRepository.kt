package com.app.motel.data.repository

import com.app.motel.data.local.NotificationDAO
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationDAO: NotificationDAO,
) {

}