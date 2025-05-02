package com.app.motel.data.repository

import com.app.motel.data.local.ComplaintDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.Complaint
import javax.inject.Inject

class ComplaintRepository @Inject constructor(
    private val complaintDAO: ComplaintDAO,
    private val roomDAO: RoomDAO,
    private val tenantDAO: TenantDAO,
) {

    suspend fun getComplaint(boardingHouseId: String): List<Complaint>{
//        return complaintDAO.getByBoardingHouseId(boardingHouseId).map { it.toModel() }
        return complaintDAO.getByBoardingHouseId().map {
            it.toModel().apply {
                room = roomDAO.getPhongById(it.roomId ?: "")?.toModel()
                tenant = tenantDAO.getById(it.submittedBy ?: "")?.toModel()
            }
        }
    }

}