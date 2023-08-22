package com.example.travle.di.modules

import android.content.Context
import com.example.travle.data.network.ApiTravle
import com.example.travle.data.network.RemoteDataSource
import com.example.travle.data.repository.HomeRepository
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