package com.app.motel.data.repository

import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.ContractDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Tenant
import javax.inject.Inject

class TenantRepository @Inject constructor(
    private val tenantDAO: TenantDAO,
) {
    suspend fun getTenantsByRoomId(roomId: String?): List<Tenant> {
        return tenantDAO.getTenantByRoomId(roomId).map { it.toModel() }
    }

    suspend fun updateTenant(tenant: Tenant): Resource<Tenant> {
        try {
            val tenantEntity = tenant.toEntity()
            tenantDAO.update(tenantEntity)
            return Resource.Success(tenantEntity.toModel())
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "Unknown error")
        }
    }
}