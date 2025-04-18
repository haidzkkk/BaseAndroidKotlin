package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.KhuTroEntity

@Dao
interface BoardingHouseDAO {

    @Query("SELECT * FROM KhuTro")
    suspend fun getAll(): List<KhuTroEntity>

    @Query("SELECT * FROM KhuTro WHERE ID = :id")
    suspend fun getById(id: String): KhuTroEntity?

    @Query("SELECT * FROM KhuTro WHERE MaChuNha = :userId")
    suspend fun getByUserId(userId: String): List<KhuTroEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(khuTro: KhuTroEntity)

    @Update
    suspend fun update(khuTro: KhuTroEntity)

    @Delete
    suspend fun delete(khuTro: KhuTroEntity)
}
