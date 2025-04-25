package com.app.motel.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.motel.data.entity.NguoiThueEntity

@Dao
interface TenantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nguoiThue: NguoiThueEntity)

    @Update
    suspend fun update(nguoiThue: NguoiThueEntity)

    @Delete
    suspend fun delete(nguoiThue: NguoiThueEntity)

    @Query("SELECT * FROM NguoiThue WHERE ID = :id")
    suspend fun getNguoiThueById(id: String): NguoiThueEntity?

    @Query("SELECT * FROM NguoiThue")
    suspend fun getTenants(): List<NguoiThueEntity>

    @Query("""
    SELECT * FROM NguoiThue 
    WHERE (:roomId IS NULL AND (MaPhong IS NULL OR MaPhong = '')) 
       OR (MaPhong = :roomId)
""")
    suspend fun getTenantByRoomId(roomId: String?): List<NguoiThueEntity>

    @Query("SELECT * FROM NguoiThue WHERE TenDangNhap = :username AND MatKhau = :password")
    suspend fun getNguoiThueByUsernameAndPassword(username: String, password: String): NguoiThueEntity?

    @Query("SELECT * FROM NguoiThue WHERE TenDangNhap = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): NguoiThueEntity?

    @Query("SELECT * FROM NguoiThue WHERE ID = :id")
    suspend fun getById(id: String): NguoiThueEntity?

    @Query("UPDATE NguoiThue SET MaPhong = :roomId, TrangThai = :status WHERE ID = :id")
    suspend fun updateRent(id: String, roomId: String?, status: String)

}
