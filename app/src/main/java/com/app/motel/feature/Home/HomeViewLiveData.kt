package com.app.motel.feature.Home

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rooms

open class HomeViewLiveData : AppViewLiveData {
     val motelsLiveData : MutableLiveData<Resource<List<Rooms>>> = MutableLiveData()
     val testString : MutableLiveData<String> = MutableLiveData()
}