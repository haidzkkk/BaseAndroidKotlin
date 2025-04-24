package com.app.motel.feature.room.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.common.ultis.containsSearch
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.entity.PhongEntity
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room

class RoomViewState: AppViewLiveData {
    val searchText = MutableLiveData<String>()
    val currentRoomState = MutableLiveData<Resource<PhongEntity.Status>>()
    val boardingRoom = MutableLiveData<Resource<List<BoardingHouse>>>()
    val roomsWithCurrentStateSearch: List<Room> get () = (boardingRoom.value?.data?.flatMap { boardingHouse -> boardingHouse.rooms ?: arrayListOf()}  ?: arrayListOf()).let {
        if(currentRoomState.value?.isSuccess() == true && currentRoomState.value?.data != null){
            it.filter { item -> item.status == currentRoomState.value?.data?.value }
        }else it
    }.filter { item -> item.roomName.containsSearch(searchText.value ?: "") }

    val createRoom = MutableLiveData<Resource<Room>>()
    val updateRoom = MutableLiveData<Resource<Room>>()
    val deleteRoom = MutableLiveData<Resource<Room>>()
    val currentRoom = MutableLiveData<Resource<Room>>()
    val currentBoardingHouse = MutableLiveData<BoardingHouse?>()

}