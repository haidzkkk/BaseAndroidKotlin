package com.history.vietnam.di.modules

import android.content.Context
import androidx.room.Room
import com.app.motel.data.network.ApiFirebase
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import com.app.motel.data.repository.AuthRepository
import com.app.motel.data.repository.HistoricalFigureRepository
import com.app.motel.data.repository.NotificationRepository
import com.app.motel.data.repository.PageRepository
import com.app.motel.data.repository.QuizRepository
import com.app.motel.data.repository.UserRepository
import com.app.motel.data.repository.SettingRepository
import com.app.motel.data.repository.TerritoryRepository
import com.app.motel.feature.profile.UserController
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.data.local.AppDatabase
import com.history.vietnam.data.local.RoomDAO
import com.history.vietnam.data.network.ApiMock
import com.history.vietnam.data.network.RemoteDataSource
import com.history.vietnam.data.repository.HomeRepository
import com.history.vietnam.ultis.AppConstants
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
    @Singleton
    fun providerFirebaseManager(): FirebaseManager = FirebaseManager()

    @Provides
    fun providerApiMotel(
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ApiMock = remoteDataSource.buildApi(
        ApiMock::class.java,
        context,
        AppConstants.MOCK_BASE_URL
    )

    @Provides
    fun providerApiWiki(
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ApiWiki = remoteDataSource.buildApi(
        ApiWiki::class.java,
        context,
        AppConstants.WIKI_BASE_URL
    )

    @Provides
    fun providerApiFirebase(
        remoteDataSource: RemoteDataSource,
        context: Context,
    ): ApiFirebase {
        val firebaseAccessToken = context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).getString(AppConstants.SHARED_PREFERENCES_FIREBASE_ACCESS_TOKEN, "").let { token ->
            if (token.isNullOrEmpty()) "Basic Y29yZV9jbGllbnQ6c2VjcmV0" else "Bearer $token"
        }

        return remoteDataSource.buildApi(
            ApiFirebase::class.java,
            context,
            AppConstants.FIREBASE__BASE_URL,
            headers = mapOf(
                "Content-Type" to "application/json",
                "Authorization" to firebaseAccessToken,
            )
        )
    }

    @Provides
    fun provideRoomDAO(db: AppDatabase): RoomDAO = db.roomDao()

    @Provides
    fun providerHomeRepository(
        api: ApiMock,
        roomDAO: RoomDAO,
        firebaseManager: FirebaseManager,
    ): HomeRepository = HomeRepository(
        api = api,
        roomDAO = roomDAO,
        firebaseManager = firebaseManager
    )

    @Provides
    fun providerHistoricalFigureRepository(
        api: ApiWiki,
        firebaseManager: FirebaseManager,
    ): HistoricalFigureRepository = HistoricalFigureRepository(
        firebaseManager = firebaseManager,
        wikiApi = api,
    )

    @Provides
    fun providerPageRepository(
        api: ApiWiki,
        firebaseManager: FirebaseManager,
    ): PageRepository = PageRepository(
        firebaseManager = firebaseManager,
        wikiApi = api,
    )

    @Provides
    fun providerSettingRepository(
        context: Context
    ): SettingRepository = SettingRepository(
        sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    )

    @Provides
    @Singleton
    fun providerSettingController(
        settingRepository: SettingRepository
    ): SettingController = SettingController(
        settingRepository = settingRepository
    )

    @Provides
    fun providerTerritoryRepository(
        firebaseManager: FirebaseManager,
    ): TerritoryRepository = TerritoryRepository(
        firebaseManager = firebaseManager
    )

    @Provides
    fun providerAuthRepository(
        firebaseManager: FirebaseManager,
    ): AuthRepository = AuthRepository(
        firebaseManager = firebaseManager
    )

    @Provides
    fun providerProfileRepository(
        context: Context,
        firebaseManager: FirebaseManager,
    ): UserRepository = UserRepository(
        prefs = context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE),
        firebaseManager = firebaseManager
    )

    @Provides
    fun providerNotificationRepository(
        context: Context,
        api: ApiFirebase,
        userRepository: UserRepository,
        firebaseManager: FirebaseManager,
    ): NotificationRepository = NotificationRepository(
        apiFirebase = api,
        userRepository = userRepository,
        firebaseManager = firebaseManager,
    )

    @Provides
    @Singleton
    fun provideProfileController(
        repository: UserRepository,
        firebaseManager: FirebaseManager,
    ): UserController {
        return UserController(
            repo = repository,
        )
    }

    @Provides
    fun provideQuizRepository(
        firebaseManager: FirebaseManager,
    ): QuizRepository {
        return QuizRepository(
            firebaseManager = firebaseManager,
        )
    }
}