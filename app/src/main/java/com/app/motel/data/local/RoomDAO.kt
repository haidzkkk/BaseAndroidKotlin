package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(roomEntity: RoomEntity)

    @Query("SELECT * FROM room")
    fun getAllRooms(): Flow<List<RoomEntity>>

    @Delete
    suspend fun deleteRoom(roomEntity: RoomEntity)
}
