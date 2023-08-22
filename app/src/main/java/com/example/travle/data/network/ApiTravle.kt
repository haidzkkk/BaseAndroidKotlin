package com.example.travle.data.network

import com.example.travle.data.model.Travle
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiTravle {

    @GET("travle")
    fun getTravle(): Observable<List<Travle>>
}