package com.app.motel.data.repository

import com.app.motel.data.entity.NguoiThueEntity
import com.app.motel.data.local.ContractDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.CommonUser
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Tenant
import javax.inject.Inject

class TenantRepository @Inject constructor(
    private val tenantDAO: TenantDAO,
    private val roomDAO: RoomDAO,
    private val contractDAO: ContractDAO,
) {
    private suspend fun fetchTenantData(tenant: Tenant): Tenant {
        if(tenant.roomId != null ) {
            tenant.room = roomDAO.getPhongById(tenant.roomId)?.toModel()
            tenant.contract = contractDAO.getByRoomTenantId(tenant.roomId, tenant.id).firstOrNull()?.toModel()
        }
        return tenant
    }

    suspend fun getTenants(): Resource<List<Tenant>> {
        return try {
            Resource.Success(tenantDAO.getTenants().map {
                fetchTenantData(it.toModel())
            })
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun getTenantsByRoomId(roomId: String?): List<Tenant> {
        return tenantDAO.getTenantByRoomId(roomId).map { it.toModel() }
    }

    suspend fun getTenantsById(id: String): Tenant? {
        return tenantDAO.getNguoiThueById(id)?.toModel()
    }

    suspend fun updateTenant(tenant: Tenant): Resource<Tenant> {
        try {
            val tenantEntity = tenant.toEntity()
            tenantDAO.update(tenantEntity)
            return Resource.Success(tenantEntity.toModel(), message = "Cập nhật thành công")
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun addTenant(tenant: Tenant): Resource<Tenant>{
        return try {
            val tenantEntity = tenant.toEntityCreate()

            if (tenantDAO.getUserByUsername(tenantEntity.tenDangNhap) != null) {
                return Resource.Error(message = "Tên đăng nhập đã tồn tại")
            }
            tenantDAO.insert(tenantEntity)
            Resource.Success(tenantEntity.toModel(), message = "Thêm thành công")
        }catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun updateTenantRentToRoom(tenantId: String, roomId: String, status: NguoiThueEntity.Status = NguoiThueEntity.Status.ACTIVE): Resource<Tenant>{
        return try {
            tenantDAO.updateRent(tenantId, roomId, status.value)
            Resource.Success(null, message = "Cập nhật thành công")
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    suspend fun removeTenantFromRoom(roomId: String): Resource<List<Tenant>?>{
        return try {
            tenantDAO.updateRentByRoomId(roomId)
            Resource.Success(data = null, message = "Cập nhật thành công")
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }
}