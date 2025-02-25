package com.app.langking.data.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    companion object{
        private const val BASE_URL = "https://63218bbbfd698dfa29f9d7c9.mockapi.io/"
    }

    public fun <API> buildApi(apiClass: Class<API>, context: Context): API{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(apiClass)
    }
}