package com.app.motel.data.local

import androidx.room.*
import com.app.motel.data.entity.ThongBaoEntity

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: ThongBaoEntity)

    @Update
    suspend fun update(notification: ThongBaoEntity)

    @Delete
    suspend fun delete(notification: ThongBaoEntity)

    @Query("SELECT * FROM ThongBao WHERE ID = :id")
    suspend fun getById(id: String): ThongBaoEntity?

    @Query("SELECT * FROM ThongBao ORDER BY NgayTao DESC")
    suspend fun getAll(): List<ThongBaoEntity>

    @Query("SELECT * FROM ThongBao WHERE MaKhuTro = :khuTroId")
    suspend fun getByKhuTro(khuTroId: String): List<ThongBaoEntity>

    @Query("SELECT * FROM ThongBao WHERE MaPhong = :phongId")
    suspend fun getByPhong(phongId: String): List<ThongBaoEntity>

    @Query("UPDATE ThongBao SET DaDoc = 1 WHERE ID = :id")
    suspend fun markAsRead(id: String)
}
