package com.app.motel.data.repository

import android.util.Log
import com.app.motel.common.service.DateConverter
import com.app.motel.common.service.DateConverter.toCalendar
import com.app.motel.data.local.BillDAO
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.ServiceDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.Bill
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Service
import java.util.Calendar
import javax.inject.Inject

class BillRepository @Inject constructor(
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
    private val billDAO: BillDAO,
    private val serviceDAO: ServiceDAO,
) {
    companion object{
        private const val SEARCH_MONTH_MAX_LENGTH = 36 // 3 year
    }

    suspend fun checkBillCreateDate(roomId: String, createdDate: Calendar): Resource<Bill> {
        return try {

            for (currentMonthSearchIndex in 0 .. SEARCH_MONTH_MAX_LENGTH) {
                val forwardDate = DateConverter.calculateMonth(
                    createdDate.time,
                    currentMonthSearchIndex)

                val bill = billDAO.getByRoomAndMonth(
                    roomId,
                    forwardDate.get(Calendar.MONTH) + 1,
                    forwardDate.get(Calendar.YEAR),
                )

                if (bill != null) {
                    return Resource.Error(message = "Hóa đơn đã cũ, hãy tạo đơn gần nhất")
                }
            }
            Resource.Success(data = null)
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    suspend fun getPreviousBill(roomId: String): Resource<Bill> {
        return try {
            for (currentMonthSearchIndex in 0 .. SEARCH_MONTH_MAX_LENGTH) {
                val previousDate = DateConverter.calculateMonth(
                    DateConverter.getCurrentDateTime(),
                    -currentMonthSearchIndex)

                val bill = billDAO.getByRoomAndMonth(
                    roomId,
                    previousDate.get(Calendar.MONTH) + 1,
                    previousDate.get(Calendar.YEAR),
                )
                Log.e("getPreviousBill", "bill $${previousDate.time} $bill")
                if (bill != null) {
                    return Resource.Success(bill.toModel())
                }
            }
            Resource.Success(data = null, message = "Không tìm thấy hóa đơn")
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    suspend fun getServiceByRoom(boardingHouse: String, roomId: String): Resource<List<Service>> {
        return try {
            val services = serviceDAO.getServiceOfRoom(boardingHouse, roomId)
            Resource.Success(services.map { it.toModel() })
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun createBill(bill: Bill): Resource<Bill>{
        return try {
            val billEntity = bill.toCreateEntity()
            billDAO.insert(billEntity)
            Resource.Success(billEntity.toModel())
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

}