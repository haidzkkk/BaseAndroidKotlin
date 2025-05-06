package com.app.motel.feature.Home

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppState
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rooms

open class HomeViewLiveData : AppState {
     val motelsLiveData : MutableLiveData<Resource<List<Rooms>>> = MutableLiveData()
     val testString : MutableLiveData<String> = MutableLiveData()
}