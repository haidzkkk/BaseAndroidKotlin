package com.example.travle.core

import androidx.lifecycle.ViewModel
import com.example.travle.TravleViewActions
import com.example.travle.ui.Home.HomeViewLiveData
import com.example.travle.ultis.DataSource
import com.example.travle.ultis.PublishDataSource

abstract class TravleBaseViewModel <S : TravleViewLiveData, VA : TravleViewActions, VE : TravleViewEvents>
    : ViewModel() {

    protected val _liveData = HomeViewLiveData()
    val liveData: HomeViewLiveData = _liveData

    // Used to post transient events to the View
    protected val _viewEvents = PublishDataSource<VE>()
    val viewEvents: DataSource<VE> = _viewEvents

    abstract fun handle(action: VA)
}