package com.app.motel.feature.Service.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Service
import com.app.motel.data.repository.ServiceRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceViewModel @Inject constructor(
    private val repository: ServiceRepository,
    private val profileController: ProfileController,
): AppBaseViewModel<ServiceViewState, ServiceViewAction, ServiceViewEvent>(
    ServiceViewState()
) {
    override fun handle(action: ServiceViewAction) {
    }

    fun initForm(item: Service?) {
        liveData.currentService.postValue(item)

        val currentBoardingHouse = liveData.boardingService.value?.data?.firstOrNull{
            it.service?.firstOrNull { service -> service.id == item?.id } != null
        } ?: liveData.boardingService.value?.data?.firstOrNull()

        liveData.currentBoardingHouse.postValue(currentBoardingHouse)
    }

    fun clearForm(){
        liveData.currentService.postValue(null)
        liveData.currentBoardingHouse.postValue(null)
        liveData.createService.postValue(Resource.Initialize())
    }

    fun getService(){
        liveData.boardingService.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val userId = profileController.state.currentUserId
                val boardingHouses = repository.getBoardingServiceByUserId(userId)
                liveData.boardingService.postValue(Resource.Success(boardingHouses))
            }catch (e: Exception){
                liveData.boardingService.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun createService(
        name: String?,
        price: String?,
        typePay: String?,
        isAppliesAllRoom: Boolean,
    ){
        liveData.createService.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createService.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            liveData.currentBoardingHouse.value == null -> {
                liveData.createService.postValue(Resource.Error(message = "Không tìm thấy khu trọ của bạn"))
                return
            }
            name.isNullOrBlank() -> {
                liveData.createService.postValue(Resource.Error(message = "Tên dịch vụ là bắt buộc"))
                return
            }
            typePay.isNullOrBlank() -> {
                liveData.createService.postValue(Resource.Error(message = "Giá dịch vụ là bắt buộc"))
                return
            }
        }

        viewModelScope.launch {
            val isCreate = liveData.currentService.value == null
            val newService = if(isCreate) Service(
                name = name!!,
                price = price ?: "0",
                typePay = typePay,
                areaId = liveData.currentBoardingHouse.value!!.id,
                roomId = if (isAppliesAllRoom) null else liveData.currentService.value?.roomId,
            ) else liveData.currentService.value!!.copy(
                name = name!!,
                price = price ?: "0",
                typePay = typePay,
                roomId = if (isAppliesAllRoom) null else liveData.currentService.value?.roomId,
            )

            val serviceCreated = if(isCreate) repository.createService(newService)
                else repository.updateService(newService)
            if(serviceCreated.isSuccess()){
                if(serviceCreated.data?.isAppliesAllRoom == true){
                    repository.updateRoomService(serviceCreated.data, liveData.currentBoardingHouse.value?.rooms ?: arrayListOf())
                }
            }
            liveData.createService.postValue(serviceCreated)
        }
    }

    fun deleteService(serviceDelete: Service?){
        liveData.createService.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createService.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            serviceDelete == null -> {
                liveData.createService.postValue(Resource.Error(message = "Không tìm thấy dịch vụ"))
                return
            }
        }

        viewModelScope.launch {
            val serviceDeleted = repository.deleteService(serviceDelete!!)

            val roomFilterTypePay = (liveData.boardingService.value?.data?.firstOrNull{ boardingHouse ->
                boardingHouse.service?.firstOrNull { service -> service.id == serviceDelete.id } != null
            }?.rooms ?: arrayListOf()).let { roomsFromBoarding ->
                if(serviceDeleted.data?.isAppliesAllRoom == true) roomsFromBoarding
                else roomsFromBoarding.filter { room -> room.id == serviceDeleted.data?.roomId }
            }

            if(serviceDeleted.isSuccess()){
                repository.updateRoomService(
                    service = serviceDeleted.data!!,
                    rooms = roomFilterTypePay,
                    isUpdate = false
                )
            }
            liveData.createService.postValue(serviceDeleted)
        }

    }
}