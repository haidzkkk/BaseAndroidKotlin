package com.app.motel.feature.Service.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Service

class ServiceViewState: AppViewLiveData {
    val boardingService = MutableLiveData<Resource<List<BoardingHouse>>>()
    val createService = MutableLiveData<Resource<Service>>()

    val currentService = MutableLiveData<Service?>()
    val currentBoardingHouse = MutableLiveData<BoardingHouse?>()

    val services get () = boardingService.value?.data?.flatMap { boardingHouse -> boardingHouse.service ?: arrayListOf() } ?: arrayListOf()
}