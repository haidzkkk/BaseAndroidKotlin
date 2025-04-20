package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.PhongEntity

@Dao
interface RoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(phong: PhongEntity)

    @Update
    suspend fun update(phong: PhongEntity)

    @Delete
    suspend fun delete(phong: PhongEntity)

    @Query("SELECT * FROM Phong WHERE ID = :id")
    suspend fun getPhongById(id: String): PhongEntity?

    @Query("SELECT * FROM Phong")
    suspend fun getAllPhongs(): List<PhongEntity>

    @Query("SELECT * FROM Phong WHERE MaKhuTro = :areaId")
    suspend fun getPhongsByKhuTroId(areaId: String): List<PhongEntity>

    @Query("SELECT * FROM Phong WHERE TrangThai = :status")
    suspend fun getPhongsByStatus(status: String): List<PhongEntity>

    @Query("UPDATE Phong SET TrangThai = :trangThai WHERE ID = :id")
    suspend fun updateStatus(id: String, trangThai: String)


}
