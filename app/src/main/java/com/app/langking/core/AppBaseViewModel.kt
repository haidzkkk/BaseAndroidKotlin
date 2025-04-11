package com.app.langking.core

import androidx.lifecycle.ViewModel
import com.app.langking.ultis.DataSource
import com.app.langking.ultis.PublishDataSource

abstract class AppBaseViewModel <S : AppViewLiveData, VA : AppViewActions, VE : AppViewEvent>(state: S)
    : ViewModel() {

    val liveData: S = state

    // Used to post transient events to the View
    protected val _viewEvents = PublishDataSource<VE>()
    val viewEvents: DataSource<VE> = _viewEvents

    abstract fun handle(action: VA)
}