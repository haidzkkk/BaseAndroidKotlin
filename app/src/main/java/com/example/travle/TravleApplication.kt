package com.example.travle

import android.app.Application
import com.example.travle.di.DaggerTravleComponent
import com.example.travle.di.TravleComponent

class TravleApplication : Application() {

    val travleComponent: TravleComponent by lazy {
        DaggerTravleComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        travleComponent.inject(this)
    }
}