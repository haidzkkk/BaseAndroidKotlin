package com.app.motel.feature.room.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.common.ultis.toStringMoney
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Service
import com.app.motel.data.model.Tenant
import com.app.motel.data.repository.ContractRepository
import com.app.motel.data.repository.RoomRepository
import com.app.motel.data.repository.ServiceRepository
import com.app.motel.data.repository.TenantRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val serviceRepository: ServiceRepository,
    private val contractRepository: ContractRepository,
    private val tenantRepository: TenantRepository,
    val userController: UserController,
): AppBaseViewModel<RoomViewState, RoomViewAction, RoomViewEvent>(
    RoomViewState()
) {
    override fun handle(action: RoomViewAction) {
    }

    fun initRoomDetail(item: Room?){
        liveData.currentRoom.value = Resource.Success(item)

        viewModelScope.launch {
            val roomFind = roomRepository.getRoomById(item?.id ?: "")
            if (roomFind?.id == null){
                return@launch
            }

            val contract: Contract? = contractRepository.getContractActiveByRoomId(roomFind.id)
            val services: Resource<List<Service>> = serviceRepository.getServiceByRoom(roomFind.areaId ?: "", roomFind.id)
            val tenants: List<Tenant> = tenantRepository.getTenantsByRoomId(roomFind.id)
            Log.e("RoomViewModel", "tenants: ${tenants}")
            roomFind.listService = services.data
            roomFind.contract = contract
            roomFind.tenants = tenants.map {
                it.room = roomFind
                it.contract = contract
                it
            }

            liveData.currentRoom.value = Resource.Success(roomFind)
        }
    }

    fun clearStateCreate(){
        liveData.currentRoom.postValue(Resource.Initialize())
        liveData.updateRoom.postValue(Resource.Initialize())
        liveData.deleteRoom.postValue(Resource.Initialize())
    }

    fun getRoom(){
        liveData.rooms.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val boardingHouseId = userController.state.currentBoardingHouseId
                val boardingHouses = roomRepository.geRoomBytBoardingHouseId(boardingHouseId)
                liveData.rooms.postValue(Resource.Success(boardingHouses))
            }catch (e: Exception){
                liveData.rooms.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun updateRoom(
        room: Room?,
        nameRoom: String?,
        area: String?,
        maxTenant: String?,
        priceRoom: String?,
    ){
        liveData.updateRoom.postValue(Resource.Loading())
        val currentUser = userController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.updateRoom.postValue(Resource.Error(message = "Bạn không có quyền sửa"))
                return
            }
            room?.id == null -> {
                liveData.updateRoom.postValue(Resource.Error(message = "Không tìm thấy phòng thuê"))
                return
            }
            nameRoom.isNullOrBlank() -> {
                liveData.updateRoom.postValue(Resource.Error(message = "Tên phòng không được để trống"))
                return
            }
            priceRoom.isNullOrBlank() -> {
                liveData.updateRoom.postValue(Resource.Error(message = "Giá phòng không được để trống"))
                return
            }
            maxTenant?.isNotEmpty() == true && (maxTenant.toIntOrNull() ?: 0) < (room.tenants?.size ?: 0)-> {
                liveData.updateRoom.postValue(Resource.Error(message = "Số lượng người thuê tối đa phải lớn hơn số lượng người thuê hiện tại"))
                return
            }
        }

        viewModelScope.launch {
            val roomUpdate = room!!.copy(
                roomName = nameRoom ?: "",
                area = area?.toDoubleOrNull(),
                maxOccupants = maxTenant?.toIntOrNull(),
                rentalPrice = priceRoom.toStringMoney()
            )

            val roomUpdated = roomRepository.updateRoom(roomUpdate)
            if(roomUpdated.isSuccess()){
                initRoomDetail(roomUpdated.data)
            }
            liveData.updateRoom.postValue(roomUpdated)
        }
    }

    fun deleteRoom(roomDelete: Room?){
        liveData.deleteRoom.postValue(Resource.Loading())
        val currentUser = userController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.deleteRoom.postValue(Resource.Error(message = "Bạn không có quyền xóa"))
                return
            }
            roomDelete == null -> {
                liveData.deleteRoom.postValue(Resource.Error(message = "Không tìm thấy dịch vụ"))
                return
            }
            roomDelete.isRenting || roomDelete.contract != null -> {
                liveData.deleteRoom.postValue(Resource.Error(message = "Phòng đang có hợp đồng không thể xóa"))
                return
            }
        }
        viewModelScope.launch {
            val roomDeleted = roomRepository.deleteRoom(roomDelete!!)

            if(roomDeleted.isSuccess()){
                val tenantRenting = tenantRepository.getTenantsByRoomId(roomDelete.id)
                tenantRenting.forEach {
                    tenantRepository.updateTenant(it.copy(roomId = null))
                }
            }
            liveData.deleteRoom.postValue(roomDeleted)
        }
    }

    fun createRoom(
        nameRoom: String?,
        area: String?,
        maxTenant: String?,
        priceRoom: String?,
    ){
        liveData.createRoom.postValue(Resource.Loading())

        val currentUser = userController.state.currentUser.value?.data
        val currentBoardingHouse = userController.state.getCurrentBoardingHouse
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createRoom.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            currentBoardingHouse?.id == null -> {
                liveData.createRoom.postValue(Resource.Error(message = "Không tìm thấy khu trọ của bạn"))
                return
            }
            nameRoom.isNullOrBlank() -> {
                liveData.createRoom.postValue(Resource.Error(message = "Tên phòng không được để trống"))
                return
            }
            priceRoom.isNullOrBlank() -> {
                liveData.createRoom.postValue(Resource.Error(message = "Giá phòng không được để trống"))
                return
            }
        }

        viewModelScope.launch {
            val roomUpdate = Room(
                roomName = nameRoom ?: "",
                area = area?.toDoubleOrNull(),
                maxOccupants = maxTenant?.toIntOrNull(),
                rentalPrice = priceRoom.toStringMoney(),
                areaId = currentBoardingHouse?.id
            )

            val roomUpdated = roomRepository.createRoom(roomUpdate)
            if(roomUpdated.isSuccess()){
                initRoomDetail(roomUpdated.data)
            }
            liveData.createRoom.postValue(roomUpdated)
        }
    }
}