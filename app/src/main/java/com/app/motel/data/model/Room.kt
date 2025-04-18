package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.PhongEntity

data class Room(
    val id: String,
    val roomName: String,
    val maxOccupants: Int?,
    val area: Double?,
    val rentalPrice: String,
    val services: String?,
    val status: String?,
    val areaId: String
    ) {

    fun toEntity(): PhongEntity {
        return PhongEntity(
            id = id,
            tenPhong = roomName,
            soNguoiToiDa = maxOccupants,
            dienTich = area,
            giaThue = rentalPrice,
            dichVu = services,
            trangThai = status,
            maKhuTro = areaId
        )
    }

    fun toCreateEntity(): PhongEntity {
        return PhongEntity(
            id = IDManager.createID(),
            tenPhong = roomName,
            soNguoiToiDa = maxOccupants,
            dienTich = area,
            giaThue = rentalPrice,
            dichVu = services,
            trangThai = PhongEntity.STATE_EMPTY,
            maKhuTro = areaId
        )
    }

}