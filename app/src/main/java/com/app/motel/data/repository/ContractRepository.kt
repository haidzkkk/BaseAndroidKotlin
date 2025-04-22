package com.app.motel.data.repository

import com.app.motel.data.entity.HopDongEntity
import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.ContractDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Tenant
import javax.inject.Inject

class ContractRepository @Inject constructor(
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
    private val contractDAO: ContractDAO,
    private val tenantDAO: TenantDAO,
) {

    suspend fun getContractByUserId(userId: String): List<Contract> {
        val contractEntities: List<HopDongEntity> = contractDAO.getContractsByUserId(userId)
        return contractEntities.map { contractEntity ->
            val roomEntity = roomDAO.getPhongById(contractEntity.maPhong)
            val tenantEntity = tenantDAO.getNguoiThueById(contractEntity.maKhach)
            contractEntity.toModel().apply {
                this.room = roomEntity?.toModel()
                this.tenant = tenantEntity?.toModel()
            }
        }
    }

    suspend fun getTenantsByRoomId(roomId: String?): List<Tenant> {
        return tenantDAO.getNguoiThueByRoomId(roomId).map { it.toModel() }
    }

    suspend fun getBoardingRoomByUserId(userId: String): List<BoardingHouse> {
        val boardingHousesEntities: List<KhuTroEntity> = boardingHouseDAO.getByUserId(userId)
        return boardingHousesEntities.map { boardingHouseEntity ->
            val roomEntities = roomDAO.getPhongsByKhuTroId(boardingHouseEntity.id)
            val boardingHouse = boardingHouseEntity.toModel()
            boardingHouse.rooms = roomEntities.map {
                it.toModel().apply {
                    tenants = getTenantsByRoomId(this.id)
                }
            }
            boardingHouse
        }
    }

    suspend fun createContract(contract: Contract): Resource<Contract>{
        return try {
            val contractEntity = contract.toCreateEntity()
            contractDAO.insert(contractEntity)
            Resource.Success(contractEntity.toModel())
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateContract(contract: Contract): Resource<Contract> {
        return try {
            val contractEntity = contract.toEntity()
            contractDAO.update(contractEntity)
            Resource.Success(contractEntity.toModel(), message = "Sửa hợp đồng thành công")
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateStateRoom(roomId: String, state: String): Resource<Boolean>{
        return try {
            roomDAO.updateStatus(roomId, state)
            Resource.Success(true)
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateUserRented(tenantId: String, roomId: String?): Resource<Boolean>{
        return try {
            tenantDAO.updateRoomId(tenantId, roomId)
            Resource.Success(true)
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }
}