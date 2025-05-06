package com.app.motel.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
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
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d("ROOM", "Room DB created from asset: ${db.path}")
                        }
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
//                            db.execSQL("PRAGMA journal_mode=DELETE") // run with with scrip top merge file database in data/data
                            db.execSQL("PRAGMA foreign_keys=ON;")
                            Log.d("ROOM", "Database opened===>: ${db.path}")
                        }
                    })
                    .build().also { INSTANCE = it }
            }
    }
}
