package com.app.motel.data.model

import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.NguoiThueEntity
import java.util.UUID
import kotlin.random.Random

data class Tenant(
    val id: String = "",
    val fullName: String,
    val idCard: String?,
    val homeTown: String?,
    val birthDay: String?,
    val phoneNumber: String?,
    val status: String? = null,
    val roomId: String? = null,
    val username: String,
    val password: String
){
    var room: Room? = null
    var contract: Contract? = null

    val isRenting: Boolean get() = status == NguoiThueEntity.Status.ACTIVE.value
    val isLock: Boolean get() = status == NguoiThueEntity.Status.TEMPORARY_ABSENT.value

    fun toEntity(): NguoiThueEntity{
        return NguoiThueEntity(
            id = id,
            hoTen = fullName,
            cccd = idCard,
            queQuan = homeTown,
            ngaySinh = birthDay,
            soDienThoai = phoneNumber,
            trangThai = status,
            maPhong = roomId,
            tenDangNhap = username,
            matKhau = password,
        )
    }

    fun toEntityCreate(): NguoiThueEntity{
        return NguoiThueEntity(
            id = IDManager.createID(),
            hoTen = fullName,
            cccd = idCard,
            queQuan = homeTown,
            ngaySinh = birthDay,
            soDienThoai = phoneNumber,
            trangThai = NguoiThueEntity.Status.INACTIVE.value,
            maPhong = roomId,
            tenDangNhap = username,
            matKhau = password,
        )
    }
}
