package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.HopDongEntity

@Dao
interface ContractDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contract: HopDongEntity)

    @Update
    suspend fun update(contract: HopDongEntity)

    @Delete
    suspend fun delete(contract: HopDongEntity)

    @Query("SELECT * FROM HopDong WHERE ID = :id")
    suspend fun getById(id: String): HopDongEntity?

    @Query("SELECT * FROM HopDong")
    suspend fun getAll(): List<HopDongEntity>

    @Query("SELECT * FROM HopDong WHERE MaKhach = :customerId")
    suspend fun getByCustomerId(customerId: String): List<HopDongEntity>

    @Query("SELECT * FROM HopDong WHERE MaPhong = :roomId")
    suspend fun getByRoomId(roomId: String): List<HopDongEntity>

    @Query("""
    SELECT HopDong.*
    FROM HopDong
    INNER JOIN Phong ON HopDong.MaPhong = Phong.ID
    INNER JOIN KhuTro ON Phong.MaKhuTro = KhuTro.ID
    WHERE KhuTro.MaChuNha = :userId
""")
    suspend fun getContractsByUserId(userId: String): List<HopDongEntity>
}
