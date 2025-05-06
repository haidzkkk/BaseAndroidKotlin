package com.app.motel.data.repository

import com.app.motel.data.entity.RoomEntity
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.model.Rooms
import com.app.motel.data.network.ApiMock
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiMock,
    private val roomDAO: RoomDAO
) {
    fun test() {
        roomDAO.getAllRooms()
        "test 123"
    }

    suspend fun getRoomsNetwork() : List<Rooms> = api.getMotel()

    suspend fun getRoomsLocal(): List<Rooms> {
        return roomDAO.getAllRooms().map { it.toModel() }
    }

    suspend fun setRoomToLocal(rooms: List<Rooms>){
        rooms.forEach {
            roomDAO.insertRoom(it.toEntity())
        }
    }


}