package com.example.travle.data.repository


import com.example.travle.data.model.Travle
import com.example.travle.data.network.ApiTravle
import com.example.travle.data.network.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiTravle) {
    public fun test() = "test 123"


    public fun getTravle() : Observable<List<Travle>> = api.getTravle().subscribeOn(Schedulers.io())
}