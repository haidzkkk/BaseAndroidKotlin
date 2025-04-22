package com.app.motel.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource

open class HomeViewLiveData : AppViewLiveData {
     val boardingHouse : MutableLiveData<Resource<List<BoardingHouse>>> = MutableLiveData()
}