package com.app.motel.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Role
import com.app.motel.data.repository.BillRepository
import com.app.motel.data.repository.ContractRepository
import com.app.motel.data.repository.HomeRepository
import com.app.motel.data.repository.RoomRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val roomRepository: RoomRepository,
    private val contractRepository: ContractRepository,
    private val billRepository: BillRepository,
    val profileController: ProfileController,
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    override fun handle(action: HomeViewAction) {
//        when(action){
//            is HomeViewAction.GetMotelViewAction -> getMotel()
//        }
    }

    fun getBoardingByUserId(){
        liveData.boardingHouse.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val user = profileController.state.currentUser.value?.data
                when{
                    user?.id.isNullOrBlank() -> {
                        liveData.boardingHouse.postValue(Resource.Error(message = "Không tìm thấy người dùng"))
                        return@launch
                    }
                    user?.isAdmin != true -> {
                        liveData.boardingHouse.postValue(Resource.Error(message = "Người dùng không phải là quản lý"))
                        return@launch
                    }
                }

                val response = roomRepository.getBoardingRoomByUserId(user?.id ?: "")
                liveData.boardingHouse.postValue(Resource.Success(response))
                Log.e("TAG", "getRoomsLocal: ${user?.id} - $response", )
            } catch (e: Exception) {
                Log.e("TAG", "getRoomsLocal: $e", )
                liveData.boardingHouse.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun getContracts(){
        liveData.contracts.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val userId = profileController.state.currentUserId
                val contracts = contractRepository.getContractByUserId(userId)
                liveData.contracts.postValue(Resource.Success(contracts))
            }catch (e: Exception){
                Log.e("HandleContractViewModel", e.toString())
                liveData.contracts.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun getBills(){
        liveData.bills.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val userId = profileController.state.currentUserId
                val bills = billRepository.getBillByUserId(userId)
                liveData.bills.postValue(Resource.Success(bills))
            }catch (e: Exception){
                liveData.bills.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun logout(){
        profileController.logout()
        liveData.boardingHouse.postValue(Resource.Initialize())
    }
}