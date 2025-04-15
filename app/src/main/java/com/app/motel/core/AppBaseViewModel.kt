package com.app.motel.core

import androidx.lifecycle.ViewModel
import com.app.motel.feature.Home.HomeViewLiveData
import com.app.motel.ultis.DataSource
import com.app.motel.ultis.PublishDataSource

abstract class AppBaseViewModel <S : AppViewLiveData, VA : AppViewActions, VE : AppViewEvent>
    : ViewModel() {

    protected val _liveData = HomeViewLiveData()
    val liveData: HomeViewLiveData = _liveData

    // Used to post transient events to the View
    protected val _viewEvents = PublishDataSource<VE>()
    val viewEvents: DataSource<VE> = _viewEvents

    abstract fun handle(action: VA)
}