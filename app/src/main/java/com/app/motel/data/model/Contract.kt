package com.app.motel.data.model

import com.app.motel.common.service.DateConverter
import com.app.motel.common.service.IDManager
import com.app.motel.data.entity.HopDongEntity
import java.util.Calendar
import java.util.concurrent.TimeUnit

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
    var room: Room? = null
    var tenant: Tenant? = null

    val state: State
        get() = State.getStateByDate(endDate ?: "")

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
        thoiHan = DateConverter.monthsBetweenDates(DateConverter.localStringToDate(startDate ?: "")!!, DateConverter.localStringToDate(endDate ?: "")!!),
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

    enum class State(
        val stateName: String
    ) {
        ACTIVE("Còn hạn"),
        NEAR_END("Sắp hết hạn"),
        ENDED("Đã hế hạn"),
        UNKNOWN("Không xác định");

        companion object {
            fun getStateByDate(date: String): State {
                val expiryDate = DateConverter.localStringToDate(date)
                val today = DateConverter.getCurrentDateTime()

                if (expiryDate == null) return UNKNOWN

                val calToday = Calendar.getInstance()
                val calExpiry = Calendar.getInstance()
                calToday.time = today
                calExpiry.time = expiryDate

                return when {
                    calExpiry.before(calToday) -> ENDED
                    getDaysBetween(calToday, calExpiry) <= 30 -> NEAR_END
                    else -> ACTIVE
                }
            }

            private fun getDaysBetween(start: Calendar, end: Calendar): Long {
                val diffMillis = end.timeInMillis - start.timeInMillis
                return TimeUnit.MILLISECONDS.toDays(diffMillis)
            }
        }
    }
}
