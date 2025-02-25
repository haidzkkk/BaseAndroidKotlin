package com.app.langking.di.modules

import android.content.Context
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
}