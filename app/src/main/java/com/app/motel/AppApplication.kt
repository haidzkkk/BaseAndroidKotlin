package com.app.motel

import android.app.Application
import com.app.motel.di.DaggerAppComponent
import com.app.motel.di.AppComponent

class AppApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}