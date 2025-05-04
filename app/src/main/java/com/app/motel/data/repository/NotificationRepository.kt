package com.app.motel.data.repository

import com.app.motel.data.local.NotificationDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.model.Notification
import com.app.motel.data.model.Resource
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val roomDAO: RoomDAO,
    private val notificationDAO: NotificationDAO,
) {

    suspend fun getNews(boardingHouseId: String): List<Notification>{
        return notificationDAO.getNewByPhongByUserId(boardingHouseId).map {
            it.toModel().apply {
                room = roomDAO.getPhongById(it.phongId ?: "")?.toModel()
            }
        }
    }

    suspend fun addNews(notification: Notification): Resource<Notification>{
        return try {
            val notificationEntity = notification.toEntityInsert()
            notificationDAO.insert(notificationEntity)
            Resource.Success(notificationEntity.toModel())
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }
}