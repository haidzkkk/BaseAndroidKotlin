package com.history.vietnam

import android.app.Application
import com.history.vietnam.di.DaggerAppComponent
import com.history.vietnam.di.AppComponent

class AppApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}