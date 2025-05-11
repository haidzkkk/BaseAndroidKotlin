package com.history.vietnam.data.network

import com.history.vietnam.data.model.Rooms
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiMock {

    @GET("rooms")
    suspend fun getMotel(): List<Rooms>
}