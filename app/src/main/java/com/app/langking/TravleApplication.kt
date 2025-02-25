package com.app.langking

import android.app.Application
import com.app.langking.di.DaggerTravleComponent
import com.app.langking.di.TravleComponent

class TravleApplication : Application() {

    val travleComponent: TravleComponent by lazy {
        DaggerTravleComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        travleComponent.inject(this)
    }
}