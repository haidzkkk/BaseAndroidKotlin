package com.history.vietnam.data.local

import androidx.room.*
import com.history.vietnam.data.entity.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(roomEntity: RoomEntity)

    @Query("SELECT * FROM room")
    fun getAllRooms(): List<RoomEntity>

    @Delete
    suspend fun deleteRoom(roomEntity: RoomEntity)
}
