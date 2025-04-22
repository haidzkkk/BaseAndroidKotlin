package com.app.motel.feature.createBill.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.Bill
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Service

class CreateBillViewState: AppViewLiveData {
    val boardingRoom = MutableLiveData<Resource<List<BoardingHouse>>>()

    val currentRoom = MutableLiveData<Resource<Room>>()
    val previousBill = MutableLiveData<Resource<Bill>>()
    val currentServiceRoom = MutableLiveData<Resource<List<Service>>>()
    val createBill = MutableLiveData<Resource<Bill>>()

    val roomsRented: List<Room> get () = (boardingRoom.value?.data?.flatMap { boardingHouse -> boardingHouse.rooms ?: arrayListOf()}  ?: arrayListOf()).filter { item -> item.isLiving}

}