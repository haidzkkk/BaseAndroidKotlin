package com.app.motel.feature.createContract.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.PhongEntity
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import com.app.motel.data.repository.ContractRepository
import com.app.motel.data.repository.RoomRepository
import com.app.motel.data.repository.TenantRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateContractViewModel @Inject constructor(
    private val repository: ContractRepository,
    private val tenantRepository: TenantRepository,
    private val roomRepository: RoomRepository,
    private val userController: UserController,
): AppBaseViewModel<CreateContractViewState, CreateContractViewAction, CreateContractViewEvent>(
    CreateContractViewState()
) {
    override fun handle(action: CreateContractViewAction) {
    }

    fun initForm(roomId: String?, tenantId: String?){
        liveData.currentRoomId = roomId
        liveData.currentTenantId = tenantId
    }

    fun clearForm(){
        liveData.currentRoomId = null

        liveData.createContract.postValue(Resource.Initialize())
        liveData.tenantNotRented.postValue(Resource.Initialize())
    }

    fun getTenantNotRented(){
        viewModelScope.launch {
            try {
                liveData.tenantNotRented.postValue(Resource.Success(tenantRepository.getTenantsByRoomId(null)))
            }catch (e: Exception){
                liveData.tenantNotRented.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun getRoom(){
        liveData.rooms.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val boardingHouseId = userController.state.currentBoardingHouseId
                val rooms = roomRepository.geRoomBytBoardingHouseId(boardingHouseId)
                liveData.rooms.postValue(Resource.Success(rooms))
            }catch (e: Exception){
                liveData.rooms.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun createContact(
        roomId: String?,
        tenantId: String?,
        nameContract: String?,
        createdDate: String?,
        startDate: String?,
        endDate: String?,
        deposit: String?,
        note: String?,
    ){
        liveData.createContract.postValue(Resource.Loading())
        val currentUser = userController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createContract.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            roomId.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Không tìm thấy phòng thuê"))
                return
            }
            tenantId.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Không tìm thấy người đại diện thuê phòng"))
                return
            }
            nameContract.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Thời gian lập hợp đồng là bắt buộc"))
                return
            }
            createdDate.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Thời gian lập hợp đồng là bắt buộc"))
                return
            }
            startDate.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Thời gian ở là bắt buộc"))
                return
            }
            endDate.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Thời gian kết thúc là bắt buộc"))
                return
            }
            deposit.isNullOrBlank() -> {
                liveData.createContract.postValue(Resource.Error(message = "Số tiền cọc là bắt buộc"))
                return
            }
        }

        viewModelScope.launch {
            val newContract = Contract(
                roomId = roomId,
                name = nameContract,
                customerId = tenantId,
                createdDate = createdDate,
                startDate = startDate,
                endDate = endDate,
                deposit = deposit,
                note = note
            )

            val contractCreated = repository.createContract(newContract)
            if(contractCreated.isSuccess()){
                repository.updateStateRoom(roomId!!, PhongEntity.Status.RENTED.value)
                tenantRepository.updateTenantRentToRoom(tenantId!!, roomId)
            }
            liveData.createContract.postValue(contractCreated)
        }
    }
}