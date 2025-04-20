package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.PhongEntity

data class Room(
    val id: String = "",
    val roomName: String,
    val maxOccupants: Int? = null,
    val area: Double? = null,
    val rentalPrice: String = "",
    val services: String? = null,
    val status: String? = null,
    val areaId: String
    ) {

    val isEmpty get() = status == PhongEntity.STATE_EMPTY
    val isLiving get() = status == PhongEntity.STATE_RENTED

    var tenants: List<Tenant>? = null

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