package com.app.motel.data.model

import com.app.motel.data.entity.ThongBaoEntity

data class Notification(
    val id: String,
    val title: String,
    val content: String?,
    val createdDate: String?,
    val khuTroId: String?,
    val phongId: String?,
    val isRead: Int = 0
) {
    fun toEntity() = ThongBaoEntity(
        id = id,
        title = title,
        content = content,
        createdDate = createdDate,
        khuTroId = khuTroId,
        phongId = phongId,
        isRead = isRead
    )
}
