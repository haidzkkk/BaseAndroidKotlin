package com.app.motel.feature.Home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Resource
import com.app.motel.data.repository.HomeRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository,
    val profileController: ProfileController,
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    override fun handle(action: HomeViewAction) {
//        when(action){
//            is HomeViewAction.GetMotelViewAction -> getMotel()
//        }
    }

    init {

        viewModelScope.launch {
            repo.addSomeTenant()
        }
    }

    fun getBoardingByUserId(){
        liveData.boardingHouse.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val userId = profileController.state.currentUserId;
                if(userId.isBlank()){
                    liveData.boardingHouse.postValue(Resource.Error(message = "Không tìm thấy người dùng"))
                }

                val response = repo.getBoardingByUserId(userId)
                liveData.boardingHouse.postValue(Resource.Success(response))
                Log.e("TAG", "getRoomsLocal: $userId - ${response}", )
            } catch (e: Exception) {
                Log.e("TAG", "getRoomsLocal: $e", )
                liveData.boardingHouse.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun logout(){
        profileController.logout()
        liveData.boardingHouse.postValue(Resource.Initialize())
    }
}