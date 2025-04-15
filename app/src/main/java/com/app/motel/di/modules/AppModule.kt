package com.app.motel.di.modules

import android.content.Context
import androidx.room.Room
import com.app.motel.data.local.AppDatabase
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.network.ApiMock
import com.app.motel.data.network.RemoteDataSource
import com.app.motel.data.repository.HomeRepository
import com.app.motel.ultis.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    fun providerRemoteDateSource(): RemoteDataSource = RemoteDataSource()

    @Provides
    @Singleton
    fun providerAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppConstants.DATABASE_NAME
    ).build()

    @Provides
    fun providerApiMotel(
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ApiMock = remoteDataSource.buildApi(ApiMock::class.java, context, AppConstants.MOCK_BASE_URL)

    @Provides
    fun provideRoomDAO(db: AppDatabase): RoomDAO = db.roomDao()

    @Provides
    fun providerHomeRepository(
        api: ApiMock,
        roomDAO: RoomDAO,
    ): HomeRepository = HomeRepository(
        api = api,
        roomDAO = roomDAO
    )
}