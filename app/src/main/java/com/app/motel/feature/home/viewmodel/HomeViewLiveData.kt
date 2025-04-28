package com.app.motel.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.Bill
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource

open class HomeViewLiveData : AppViewLiveData {
     val boardingHouse : MutableLiveData<Resource<List<BoardingHouse>>> = MutableLiveData()
     val contracts : MutableLiveData<Resource<List<Contract>>> = MutableLiveData()
     val bills = MutableLiveData<Resource<List<Bill>>>()

     val getBoardingSelected: BoardingHouse? get () = boardingHouse.value?.data?.firstOrNull()
}