package com.app.motel.di.modules

import android.content.Context
import com.app.motel.data.local.AppDatabase
import com.app.motel.data.network.ApiMock
import com.app.motel.data.network.RemoteDataSource
import com.app.motel.data.repository.AuthRepository
import com.app.motel.data.repository.HomeRepository
import com.app.motel.common.AppConstants
import com.app.motel.data.repository.BoardingHouseRepository
import com.app.motel.data.repository.CreateContractRepository
import com.app.motel.data.repository.ProfileRepository
import com.app.motel.feature.profile.ProfileController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    fun providerRemoteDateSource(): RemoteDataSource = RemoteDataSource()

    @Provides
    @Singleton
    fun providerAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun providerApiMotel(
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ApiMock = remoteDataSource.buildApi(ApiMock::class.java, context, AppConstants.MOCK_BASE_URL)

    @Provides
    fun providerHomeRepository(
        api: ApiMock,
        db: AppDatabase,
    ): HomeRepository = HomeRepository(
        api = api,
        roomDAO = db.roomDao(),
        boardingHouseDAO = db.boardingHouseDao(),
        tenantDAO = db.tenantDao(),
    )

    @Provides
    fun providerAuthRepository(
        api: ApiMock,
        db: AppDatabase,
    ): AuthRepository = AuthRepository(
        api = api,
        userDAO = db.userDao(),
        tenantDAO = db.tenantDao(),
    )

    @Provides
    fun providerBoardingHouseRepository(
        db: AppDatabase,
    ): BoardingHouseRepository = BoardingHouseRepository(
        boardingHouseDAO = db.boardingHouseDao(),
        roomDAO = db.roomDao(),
    )

    @Provides
    fun providerProfileRepository(
        context: Context,
        db: AppDatabase,
    ): ProfileRepository = ProfileRepository(
        prefs = context.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE),
        userDAO = db.userDao(),
        tenantDAO = db.tenantDao(),
    )

    @Provides
    fun providerCreateContractRepository(
        db: AppDatabase,
    ): CreateContractRepository = CreateContractRepository(
        boardingHouseDAO = db.boardingHouseDao(),
        roomDAO = db.roomDao(),
        contractDAO = db.contractDao(),
        tenantDAO = db.tenantDao(),
    )

    @Provides
    @Singleton
    fun provideProfileController(
        repository: ProfileRepository
    ): ProfileController {
        return ProfileController(
            repo = repository,
        )
    }
}