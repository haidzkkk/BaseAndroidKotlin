package com.app.motel.feature.rules.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.QuyDinhEntity
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rules
import com.app.motel.data.repository.RulesRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class RulesViewModel @Inject constructor(
    private val rulesRepository: RulesRepository,
    private val profileController: ProfileController,
): AppBaseViewModel<RulesViewState, RulesViewAction, RulesViewEvent>(
    RulesViewState()
) {
    override fun handle(action: RulesViewAction) {

    }

    fun getRules(){
        viewModelScope.launch {
           try {
               val boardingRules = rulesRepository.getRulesByUserId(profileController.state.currentUserId)
                liveData.boardingHouseRules.postValue(Resource.Success(boardingRules))
           }catch (e: Exception){
               liveData.boardingHouseRules.postValue(Resource.Error(message = e.message ?: "Unknown error"))
           }

        }
    }

    fun handleRules(rule: Rules, isUpdate: Boolean = true){
        val currentUser = profileController.state.currentUser.value?.data

        val boardingHouse: BoardingHouse? = liveData.boardingHouseRules.value?.data.let {
            it?.firstOrNull{boardingHouse -> boardingHouse.id == rule.boardingHouseId}
                ?: it?.firstOrNull()
        }

        when{
            currentUser?.isAdmin != true -> {
                liveData.addRules.postValue(Resource.Error(message = "Bạn không có quyền thêm nội quy"))
                return
            }
            rule.boardingHouseId == null -> {
                liveData.addRules.postValue(Resource.Error(message = "Không tìm thấy khu trọ"))
                return
            }
            rule.title.isBlank() -> {
                liveData.addRules.postValue(Resource.Error(message = "Tiêu đề không được để trống"))
                return
            }
            rule.content.isNullOrBlank() -> {
                liveData.addRules.postValue(Resource.Error(message = "Nội dung không được để trống"))
                return
            }
        }
        if(boardingHouse!!.rules == null) boardingHouse.rules = arrayListOf()

        val positionExits: Int = boardingHouse.rules!!.indexOfFirst { it.id == rule.id }
        if(isUpdate) {
            if(positionExits != -1) boardingHouse.rules!![positionExits] = rule
            else boardingHouse.rules!!.add(rule.copy(boardingHouseId = boardingHouse.id))
        }
        else {
            if(positionExits != -1) boardingHouse.rules!![positionExits] = rule.copy(status = QuyDinhEntity.STATUS_INACTIVE)
        }
        liveData.boardingHouseRules.postValue(Resource.Success(liveData.boardingHouseRules.value!!.data!!))
        liveData.addRules.postValue(Resource.Success(rule))
    }

    fun saveRules(rules: List<Rules>){
        val currentUser = profileController.state.currentUser.value?.data

        when{
            currentUser?.isAdmin != true -> {
                liveData.addRules.postValue(Resource.Error(message = "Bạn không có quyền thêm nội quy"))
                return
            }
        }

        viewModelScope.launch {
            liveData.saveRules.postValue(rulesRepository.saveRules(rules))
        }
    }
}