package com.app.langking.di.modules

import android.content.Context
import com.app.langking.BuildConfig
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.local.DatabaseHelper
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.local.LocalRepository
import com.app.langking.data.local.UserProfileDAO
import com.app.langking.data.network.ApiTravle
import com.app.langking.data.network.ChatRepository
import com.app.langking.data.network.FirebaseManager
import com.app.langking.data.network.RemoteDataSource
import com.app.langking.data.repository.AuthRepository
import com.app.langking.data.repository.HomeRepository
import com.app.langking.data.repository.LessonRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.feature.inbox.ChatViewModel
import com.app.langking.ultis.AppConstants
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    fun providerRemoteDateSource(): RemoteDataSource = RemoteDataSource()

    @Provides
    fun providerApiTravle(
        remoteDataSource: RemoteDataSource,
        context: Context
    ): ApiTravle = remoteDataSource.buildApi(ApiTravle::class.java, context)

    @Provides
    @Singleton
    fun providerFirebaseManager(): FirebaseManager = FirebaseManager()

    @Provides
    fun providerHomeRepository(
        api: ApiTravle,
        firebaseManager: FirebaseManager,
        dbManager: DatabaseManager,
    ): HomeRepository = HomeRepository(api, firebaseManager, dbManager)

    @Provides
    fun providerAccountRepository(
        context: Context
    ): AccountDAO = AccountDAO(context)

    @Provides
    fun providerUserProfileRepository(
        context: Context
    ): UserProfileDAO = UserProfileDAO(context )

    @Provides
    fun providerDatabaseHelper(
        context: Context
    ): DatabaseHelper = DatabaseHelper(context)

    @Provides
    fun providerDatabaseManager(
        context: Context
    ): DatabaseManager = DatabaseManager(context)

    @Provides
    fun providerLocalRepository(
        context: Context
    ): LocalRepository = LocalRepository(context.getSharedPreferences(AppConstants.prefsKey, Context.MODE_PRIVATE))

    @Provides
    fun ChatRepository(
        context: Context
    ): ChatRepository = ChatRepository(
        GenerativeModel(
            modelName = "gemini-1.5-pro-latest",
            apiKey = BuildConfig.GEMINI_KEY
        )
    )

    @Provides
    fun provideAuthRepository(
        firebaseManager: FirebaseManager,
    ): AuthRepository = AuthRepository(
        firebaseManager = firebaseManager,
    )

    @Provides
    fun provideLessonRepository(
        firebaseManager: FirebaseManager,
    ): LessonRepository = LessonRepository(
        firebaseManager = firebaseManager,
    )

    @Provides
    @Singleton
    fun provideUserRepository(
        context: Context,
        firebaseManager: FirebaseManager
    ): UserRepository {
        return UserRepository(
            context.getSharedPreferences(AppConstants.prefsKey, Context.MODE_PRIVATE),
            firebaseManager
        )
    }

    @Provides
    @Singleton
    fun provideChatViewModel(
        repo: ChatRepository,
        userRepository: UserRepository,
        dbManager: DatabaseManager,
    ): ChatViewModel {
        return ChatViewModel(
            repo = repo,
            userRepository = userRepository,
            dbManager = dbManager,
        )
    }
}