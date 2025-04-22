package com.app.motel.feature.boardingHouse.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource

class BoardingHouseState : AppViewLiveData {
    val createBoardingHouse = MutableLiveData<Resource<BoardingHouse>>()
}