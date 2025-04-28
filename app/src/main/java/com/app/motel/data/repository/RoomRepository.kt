package com.app.motel.data.repository

import androidx.room.Transaction
import com.app.motel.data.entity.HopDongEntity
import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.ContractDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
    private val contractDAO: ContractDAO,
    private val tenantDAO: TenantDAO,
    private val tenantRepository: TenantRepository,
) {

    suspend fun getBoardingRoomByUserId(userId: String): List<BoardingHouse> {
        val boardingHousesEntities: List<KhuTroEntity> = boardingHouseDAO.getByUserId(userId)
        return boardingHousesEntities.map { boardingHouseEntity ->
            val roomEntities = roomDAO.getPhongsByKhuTroId(boardingHouseEntity.id)
            val boardingHouse = boardingHouseEntity.toModel()
            boardingHouse.rooms = roomEntities.map {
                it.toModel().apply {
                    tenants = tenantRepository.getTenantsByRoomId(this.id)
                }
            }
            boardingHouse
        }
    }

    suspend fun getRoomById(roomId: String): Room? {
        val roomEntity = roomDAO.getPhongById(roomId)
        return roomEntity?.toModel()
    }

    suspend fun createRoom(room: Room): Resource<Room>{
        return try {
            val roomEntity = room.toCreateEntity()
            roomDAO.insert(roomEntity)
            Resource.Success(roomEntity.toModel())
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateRoom(room: Room): Resource<Room> {
        return try {
            val roomEntity = room.toEntity()
            roomDAO.update(roomEntity)
            Resource.Success(roomEntity.toModel())
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    @Transaction
    suspend fun deleteRoom(room: Room): Resource<Room> {
        return try {
            val roomEntity = room.toEntity()

            roomDAO.delete(roomEntity)
            Resource.Success(roomEntity.toModel())
        }catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

}