package com.app.motel.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.entity.NguoiDungEntity
import com.app.motel.data.entity.NguoiThueEntity
import com.app.motel.data.entity.PhongEntity
import com.app.motel.common.AppConstants
import com.app.motel.common.service.DateRoomConverters
import com.app.motel.common.service.StringListRoomConverter
import com.app.motel.data.entity.DichVuEntity
import com.app.motel.data.entity.HoaDonEntity
import com.app.motel.data.entity.HopDongEntity
import com.app.motel.data.entity.KhieuNaiEntity
import com.app.motel.data.entity.QuyDinhEntity
import com.app.motel.data.entity.ThongBaoEntity

@Database(entities = [
    NguoiDungEntity::class,
    KhuTroEntity::class,
    NguoiThueEntity::class,
    PhongEntity::class,
    HopDongEntity::class,
    DichVuEntity::class,
    HoaDonEntity::class,
    QuyDinhEntity::class,
    KhieuNaiEntity::class,
    ThongBaoEntity::class,
], version = 1)
@TypeConverters(StringListRoomConverter::class, DateRoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardingHouseDao(): BoardingHouseDAO
    abstract fun userDao(): UserDAO
    abstract fun tenantDao(): TenantDAO
    abstract fun roomDao(): RoomDAO
    abstract fun contractDao(): ContractDAO
    abstract fun serviceDao(): ServiceDAO
    abstract fun billDao(): BillDAO
    abstract fun rulesDAO(): RulesDAO
    abstract fun complaintDao(): ComplaintDAO
    abstract fun notificationDao(): NotificationDAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.DATABASE_NAME
                )
                    .createFromAsset(AppConstants.DATABASE_FILE_IMPORT)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d("ROOM", "Room DB created from asset: ${db.path}")
                        }
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            db.execSQL("PRAGMA foreign_keys=ON;")
                            Log.d("ROOM", "Database opened===>: ${db.path}")
                        }
                    })
                    .build().also { INSTANCE = it }
            }
    }
}