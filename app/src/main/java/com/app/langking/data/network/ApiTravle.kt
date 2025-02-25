package com.app.langking.data.network

import com.app.langking.data.model.Travle
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiTravle {

    @GET("travle")
    fun getTravle(): Observable<List<Travle>>
}