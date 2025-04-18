package com.app.motel.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.app.motel.data.model.Tenant

@Entity(
    tableName = "NguoiThue",
    foreignKeys = [ForeignKey(
        entity = PhongEntity::class,
        parentColumns = ["ID"],
        childColumns = ["MaPhong"]
    )]
)
data class NguoiThueEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: String,

    @ColumnInfo(name = "HoTen")
    val hoTen: String,

    @ColumnInfo(name = "CCCD")
    val cccd: String? = null,

    @ColumnInfo(name = "QueQuan")
    val queQuan: String? = null,

    @ColumnInfo(name = "NgaySinh")
    val ngaySinh: String? = null,

    @ColumnInfo(name = "SoDienThoai")
    val soDienThoai: String? = null,

    @ColumnInfo(name = "TrangThai")
    val trangThai: String? = null,

    @ColumnInfo(name = "MaPhong")
    val maPhong: String? = null,

    @ColumnInfo(name = "TenDangNhap")
    val tenDangNhap: String,

    @ColumnInfo(name = "MatKhau")
    val matKhau: String
){
    companion object {
        const val STATE_ACTIVE = "Đang thuê"
        const val STATE_INACTIVE = "Đã rời đi"
        const val STATE_TEMPORARY_ABSENT = "Tạm vắng"
    }

    fun toModel() = Tenant(
        id = id,
        fullName = hoTen,
        idCard = cccd,
        homeTown = queQuan,
        birthDate = ngaySinh,
        phoneNumber = soDienThoai,
        status = trangThai,
        roomId = maPhong,
        username = tenDangNhap,
        password = matKhau,
    )
}
