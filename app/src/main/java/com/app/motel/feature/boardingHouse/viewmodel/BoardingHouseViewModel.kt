package com.app.motel.feature.boardingHouse.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.app.motel.common.ultis.formatRoomName
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.repository.BoardingHouseRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class BoardingHouseViewModel @Inject constructor(
    private val boardingRepository: BoardingHouseRepository,
    private val profileController: ProfileController,
) : AppBaseViewModel<BoardingHouseState, BoardingHouseViewAction, BoardingHouseViewEvent>(
    BoardingHouseState()
) {

    override fun handle(action: BoardingHouseViewAction) {
    }

    @SuppressLint("DefaultLocale")
    fun createBoardingHouse(
        name: String?,
        roomCount: Int?,
        address: String?,
    ){
        liveData.createBoardingHouse.postValue(Resource.Loading())

        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createBoardingHouse.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            name.isNullOrBlank() -> {
                liveData.createBoardingHouse.postValue(Resource.Error(message = "Tên không được để trống"))
                return
            }
            address.isNullOrBlank() -> {
                liveData.createBoardingHouse.postValue(Resource.Error(message = "Dịa chỉ không được để trống"))
                return
            }
        }

        viewModelScope.launch {
            val newBoardingHouse = BoardingHouse(
                name = name!!,
                address = address!!,
                roomCount = roomCount,
                ownerId = currentUser!!.id,
            )

            val boardingHouseCreated = boardingRepository.createBoardingHouse(newBoardingHouse)
            if(boardingHouseCreated.isSuccess() && (boardingHouseCreated.data?.roomCount ?: 0) > 0){
                for (i in 1..boardingHouseCreated.data!!.roomCount!!) {
                    val newRoom = Room(
                        roomName = "Phòng ${i.formatRoomName()}",
                        rentalPrice = "2 000 000",
                        areaId = boardingHouseCreated.data.id,
                    )
                    boardingRepository.createRoom(newRoom)
                }
            }
            liveData.createBoardingHouse.postValue(boardingHouseCreated)
        }
    }
}