package com.app.motel.data.repository

import android.util.Log
import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.ServiceDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Service
import javax.inject.Inject

class ServiceRepository@Inject constructor(
    private val serviceDAO: ServiceDAO,
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
) {

    suspend fun getBoardingServiceByUserId(userId: String): List<BoardingHouse> {
        val boardingHousesEntities: List<KhuTroEntity> = boardingHouseDAO.getByUserId(userId)
        return boardingHousesEntities.map { boardingHouseEntity ->
            val roomEntities = roomDAO.getPhongsByKhuTroId(boardingHouseEntity.id)
            val serviceEntities = serviceDAO.getServiceByKhuTroId(boardingHouseEntity.id)
            val boardingHouse = boardingHouseEntity.toModel()
            boardingHouse.service = serviceEntities.map { it.toModel() }
            boardingHouse.rooms = roomEntities.map { it.toModel() }
            boardingHouse
        }
    }

    suspend fun createService(service: Service): Resource<Service> {
        return try {
            val serviceEntity = service.toCreateEntity()
            serviceDAO.insert(serviceEntity)
            Resource.Success(serviceEntity.toModel(), message = "Thêm dịch vụ thành công")
        }catch (e: Exception){
            Log.e("TAG", "updateService: ${e.toString()}", )
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateService(service: Service): Resource<Service> {
        return try {
            val serviceEntity = service.toEntity()
            serviceDAO.update(serviceEntity)
            Resource.Success(serviceEntity.toModel(), message = "Sửa dịch vụ thành công")
        }catch (e: Exception){
            Log.e("TAG", "updateService: ${e.toString()}", )
            Resource.Error(message = e.toString())
        }
    }

    suspend fun deleteService(service: Service): Resource<Service> {
        return try {
            val serviceEntity = service.toEntity()
            serviceDAO.delete(serviceEntity)
            Resource.Success(serviceEntity.toModel(), message = "Xóa dịch vụ thành công")
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun updateRoomService(
        service: Service,
        rooms: List<Room>,
        isUpdate: Boolean = true
    ): Resource<List<Room>> {
        return try {
            Log.e("TAG", "updateServiceRoom: ${rooms}")
            for (room in rooms){
                // update service to room
                if(isUpdate){
                    if(room.services?.contains(service.name) != true){
                        if(room.services == null){
                            room.services = service.name
                        }else{
                            room.services += ",${service.name}"
                        }
                        roomDAO.update(room.toEntity())
                        Log.e("TAG", "updateServiceRoom update: ${room.services}")
                    }
                // remove service to room
                }else{
                    Log.e("TAG", "updateServiceRoom delete: ${room.services}")
                    if(room.services?.contains(service.name) == true){
                        room.services = room.services?.replace(",${service.name}", "")?.replace(service.name, "")
                        roomDAO.update(room.toEntity())
                    }
                }

            }
            Resource.Success(rooms)
        }   catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }
}