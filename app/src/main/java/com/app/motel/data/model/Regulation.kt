package com.app.motel.data.model

import com.app.motel.data.entity.QuyDinhEntity

data class Regulation(
    val id: String,
    val title: String,
    val content: String?,
    val createdDate: String?,
    val khuTroId: String,
    val status: Int = 1
){
    fun fromModel() = QuyDinhEntity(
        id = id,
        title = title,
        content = content,
        createdDate = createdDate,
        khuTroId = khuTroId,
        status = status
    )
}
