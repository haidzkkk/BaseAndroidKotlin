package com.app.motel.feature.createContract.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.PhongEntity
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import com.app.motel.data.repository.ContractRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateContractViewModel @Inject constructor(
    private val repository: ContractRepository,
    private val profileController: ProfileController,
): AppBaseViewModel<CreateContractViewState, CreateContractViewAction, CreateContractViewEvent>(
    CreateContractViewState()
) {
    override fun handle(action: CreateContractViewAction) {
    }

    fun clearStateCreate(){
        liveData.createContract.postValue(Resource.Initialize())
        liveData.tenantNotRented.postValue(Resource.Initialize())
    }

    fun getTenantNotRented(){
        viewModelScope.launch {
            try {
                liveData.tenantNotRented.postValue(Resource.Success(repository.getTenantsByRoomId(null)))
            }catch (e: Exception){
                liveData.tenantNotRented.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun getRoom(){
        liveData.boardingRoom.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val userId = profileController.state.currentUserId
                val boardingHouses = repository.getBoardingRoomByUserId(userId)
                liveData.boardingRoom.postValue(Resource.Success(boardingHouses))
            }catch (e: Exception){
                liveData.boardingRoom.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun createContact(
        room: Room?,
        tenant: Tenant?,
        createdDate: String?,
        startDate: String?,
        endDate: String?,
        deposit: String?,
        note: String?,
    ){
        liveData.createContract.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.createContract.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            room == null -> {
                liveData.createContract.postValue(Resource.Error(message = "Không tìm thấy phòng thuê"))
                return
            }
            tenant == null -> {
                liveData.createContract.postValue(Resource.Error(message = "Không tìm thấy người đại diện thuê phòng"))
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
                roomId = room!!.id,
                customerId = tenant!!.id,
                createdDate = createdDate,
                startDate = startDate,
                endDate = endDate,
                deposit = deposit,
                note = note
            )

            val contractCreated = repository.createContract(newContract)
            if(contractCreated.isSuccess()){
                repository.updateStateRoom(room.id, PhongEntity.STATE_RENTED)
                repository.updateUserRented(tenant.id, room.id)
            }
            liveData.createContract.postValue(contractCreated)
        }
    }
}