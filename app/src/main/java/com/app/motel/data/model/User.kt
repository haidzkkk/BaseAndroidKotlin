package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.NguoiDungEntity

data class User(
    val id: String,
    val username: String,
    val password: String,
    val fullName: String,
    val birthDate: String?,
    val phoneNumber: String?,
    val email: String?,
    val state: Int,
){

    fun toEntity(): NguoiDungEntity {
        return NguoiDungEntity(
            id = id,
            tenDangNhap = username,
            matKhau = password,
            hoTen = fullName,
            ngaySinh = birthDate,
            soDienThoai = phoneNumber,
            email = email,
            trangThai = state
        )
    }

    fun toEntityRegister(): NguoiDungEntity {
        return NguoiDungEntity(
            id = IDManager.createID(),
            tenDangNhap = username,
            matKhau = password,
            hoTen = fullName,
            ngaySinh = birthDate,
            soDienThoai = phoneNumber,
            email = email,
            trangThai = NguoiDungEntity.STATE_ACTIVE
        )
    }
}
