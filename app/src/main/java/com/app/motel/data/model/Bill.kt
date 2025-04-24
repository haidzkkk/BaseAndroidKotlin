package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.HoaDonEntity

data class Bill(
    val id: String = "",
    val name: String? = "",
    val createdDate: String?,
    val roomPrice: Double,
    val waterUsage: Int?,
    val electricityUsage: Int?,
    val serviceFee: String?,
    val discount: String?,
    val totalAmount: String?,
    val status: Int? = HoaDonEntity.STATUS_UNPAID,
    val roomId: String? = null,
    val month: Int,
    val year: Int,
    val note: String?
){
    fun toEntity(): HoaDonEntity {
        return HoaDonEntity(
            id = id,
            ten = name,
            createdDate = createdDate,
            rentPrice = roomPrice,
            waterUsed = waterUsage,
            electricityUsed = electricityUsage,
            serviceFee = serviceFee,
            discount = discount ?: "0",
            total = totalAmount,
            status = status,
            roomId = roomId,
            month = month,
            year = year,
            note = note
        )
    }

    fun toCreateEntity(): HoaDonEntity {
        return HoaDonEntity(
            id = IDManager.createID(),
            ten = name,
            createdDate = createdDate,
            rentPrice = roomPrice,
            waterUsed = waterUsage,
            electricityUsed = electricityUsage,
            serviceFee = serviceFee,
            discount = discount ?: "0",
            total = totalAmount,
            status = HoaDonEntity.STATUS_UNPAID,
            roomId = roomId,
            month = month,
            year = year,
            note = note
        )
    }
}
