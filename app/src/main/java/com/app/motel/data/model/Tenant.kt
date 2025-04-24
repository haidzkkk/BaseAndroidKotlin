package com.app.motel.data.model

import com.app.motel.data.entity.NguoiThueEntity
import java.util.UUID

data class Tenant(
    val id: String = UUID.randomUUID().toString(),
    val fullName: String,
    val idCard: String?,
    val homeTown: String?,
    val birthDate: String?,
    val phoneNumber: String?,
    val status: String?,
    val roomId: String?,
    val username: String,
    val password: String
){
    fun toEntity(): NguoiThueEntity{
        return NguoiThueEntity(
            id = id,
            hoTen = fullName,
            cccd = idCard,
            queQuan = homeTown,
            ngaySinh = birthDate,
            soDienThoai = phoneNumber,
            trangThai = status,
            maPhong = roomId,
            tenDangNhap = username,
            matKhau = password,
        )
    }
}
