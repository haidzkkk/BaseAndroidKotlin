package com.app.motel.data.model

import com.app.motel.data.entity.KhieuNaiEntity

data class Complaint(
    val id: String,
    val title: String,
    val content: String?,
    val createdDate: String?,
    val submittedBy: String,
    val roomId: String,
    val status: String?
){
    fun toEntity() = KhieuNaiEntity(
        id = id,
        title = title,
        content = content,
        createdDate = createdDate,
        submittedBy = submittedBy,
        roomId = roomId,
        status = status
    )
}
