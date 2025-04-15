package com.app.motel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.motel.data.entity.RoomEntity
import com.app.motel.ultis.AppConstants
import com.app.motel.ultis.StringListConverter

@Database(entities = [RoomEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDAO

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
    }
}
