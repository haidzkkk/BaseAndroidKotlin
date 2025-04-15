package com.app.motel.data.network

import com.app.motel.data.model.Rooms
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiMock {

    @GET("rooms")
    suspend fun getMotel(): List<Rooms>
}