package com.app.langking.di.modules

import android.content.Context
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.local.DatabaseHelper
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.local.UserProfileDAO
import com.app.langking.data.network.ApiTravle
import com.app.langking.data.network.RemoteDataSource
import com.app.langking.data.repository.HomeRepository
import dagger.Module
import dagger.Provides

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
    fun providerHomeRepository(
        api: ApiTravle
    ): HomeRepository = HomeRepository(api)

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
}