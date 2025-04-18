package com.app.motel.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.app.motel.data.model.Room
import java.util.UUID

@Entity(
    tableName = "Phong",
    foreignKeys = [ForeignKey(
        entity = KhuTroEntity::class,
        parentColumns = ["ID"],
        childColumns = ["MaKhuTro"]
    )]
)
data class PhongEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: String,

    @ColumnInfo(name = "TenPhong")
    val tenPhong: String,

    @ColumnInfo(name = "SoNguoiToiDa")
    val soNguoiToiDa: Int? = null,

    @ColumnInfo(name = "DienTich")
    val dienTich: Double? = null,

    @ColumnInfo(name = "GiaThue")
    val giaThue: String,

    @ColumnInfo(name = "DichVu")
    val dichVu: String? = null,

    @ColumnInfo(name = "TrangThai")
    val trangThai: String? = null,

    @ColumnInfo(name = "MaKhuTro")
    val maKhuTro: String
){
    companion object{
        const val STATE_EMPTY = "Trống"
        const val STATE_RENTED = "Đã thuê"
        const val STATE_REPAIRING = "Đang sửa chữa"
        const val STATE_DEPOSIT = "Đặt cọc"
    }

    fun toModel(): Room {
        return Room(
            id = id,
            roomName = tenPhong,
            maxOccupants = soNguoiToiDa,
            area = dienTich,
            rentalPrice = giaThue,
            services = dichVu,
            status = trangThai,
            areaId = maKhuTro
        )
    }
}
