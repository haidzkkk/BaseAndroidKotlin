package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.QuyDinhEntity

@Dao
interface RegulationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegulation(regulation: QuyDinhEntity)

    @Update
    suspend fun updateRegulation(regulation: QuyDinhEntity)

    @Delete
    suspend fun deleteRegulation(regulation: QuyDinhEntity)

    @Query("SELECT * FROM QuyDinh WHERE ID = :id")
    suspend fun getRegulationById(id: String): QuyDinhEntity?

    @Query("SELECT * FROM QuyDinh ORDER BY NgayTao DESC")
    suspend fun getAllRegulations(): List<QuyDinhEntity>

    @Query("SELECT * FROM QuyDinh WHERE MaKhuTro = :khuTroId")
    suspend fun getRegulationsByKhuTro(khuTroId: String): List<QuyDinhEntity>
}
