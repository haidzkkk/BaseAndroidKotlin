package com.app.motel.data.model

import com.app.motel.data.entity.KhieuNaiEntity
import com.app.motel.data.local.TenantDAO

data class Complaint(
    val id: String,
    val title: String,
    val content: String?,
    val createdDate: String?,
    val submittedBy: String? = null,
    val roomId: String? = null,
    val status: String?
){
    var room: Room? = null
    var tenant: Tenant? = null

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
