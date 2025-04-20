package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.HopDongEntity

data class Contract(
    val id: String = "",
    val duration: Int? = null,
    val createdDate: String? = null,
    val startDate: String?,
    val endDate: String?,
    val deposit: String?,
    val status: Int? = null,
    val isActive: Int? = null,
    val roomId: String,
    val customerId: String,
    val note: String?
) {
    fun toEntity() = HopDongEntity(
        id = id,
        thoiHan = duration,
        ngayLapHopDong = createdDate,
        ngayBatDau = startDate,
        ngayKetThuc = endDate,
        tienCoc = deposit,
        trangThai = status,
        hieuLuc = isActive,
        maPhong = roomId,
        maKhach = customerId,
        ghiChu = note
    )

    fun toCreateEntity() = HopDongEntity(
        id = IDManager.createID(),
        thoiHan = duration,
        ngayLapHopDong = createdDate,
        ngayBatDau = startDate,
        ngayKetThuc = endDate,
        tienCoc = deposit,
        trangThai = HopDongEntity.STATE_ACTIVE,
        hieuLuc = HopDongEntity.ACTIVE,
        maPhong = roomId,
        maKhach = customerId,
        ghiChu = note
    )
}
