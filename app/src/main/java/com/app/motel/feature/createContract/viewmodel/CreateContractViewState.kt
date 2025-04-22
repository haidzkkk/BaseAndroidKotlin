package com.app.motel.feature.createContract.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant

class CreateContractViewState: AppViewLiveData {
    val createContract = MutableLiveData<Resource<Contract>>()
    val boardingRoom = MutableLiveData<Resource<List<BoardingHouse>>>()
    val tenantNotRented = MutableLiveData<Resource<List<Tenant>>>()

    val roomsNotRented: List<Room> get () = (boardingRoom.value?.data?.flatMap { boardingHouse -> boardingHouse.rooms ?: arrayListOf()}  ?: arrayListOf()).filter { item -> item.isEmpty}

}