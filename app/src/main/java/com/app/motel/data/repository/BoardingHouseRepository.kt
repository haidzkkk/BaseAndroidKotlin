package com.app.motel.data.repository

import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.local.UserDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.network.ApiMock
import javax.inject.Inject

class BoardingHouseRepository @Inject constructor(
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
) {

    suspend fun createBoardingHouse(boardingHouse: BoardingHouse): Resource<BoardingHouse>{
        return try {
            boardingHouseDAO.insert(boardingHouse.toCreateEntity())
            Resource.Success(boardingHouse)
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }

    suspend fun createRoom(room: Room): Resource<Room>{
        return try {
            roomDAO.insert(room.toCreateEntity())
            Resource.Success(room)
        }catch (e: Exception){
            Resource.Error(message = e.toString())
        }
    }
}