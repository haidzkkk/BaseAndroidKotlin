package com.app.motel.feature.rules.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.QuyDinhEntity
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rules
import com.app.motel.data.repository.RulesRepository
import com.app.motel.feature.profile.UserController
import kotlinx.coroutines.launch
import javax.inject.Inject

class RulesViewModel @Inject constructor(
    private val rulesRepository: RulesRepository,
    private val userController: UserController,
): AppBaseViewModel<RulesViewState, RulesViewAction, RulesViewEvent>(
    RulesViewState()
) {
    override fun handle(action: RulesViewAction) {

    }

    fun getRules(){
        viewModelScope.launch {
           try {
               val boardingRules = rulesRepository.getRulesByUserId(userController.state.currentBoardingHouseId)
                liveData.rules.postValue(Resource.Success(boardingRules))
           }catch (e: Exception){
               liveData.rules.postValue(Resource.Error(message = e.message ?: "Unknown error"))
           }

        }
    }

    fun handleRules(rule: Rules, isUpdate: Boolean = true){
        val currentUser = userController.state.currentUser.value?.data

        when{
            currentUser?.isAdmin != true -> {
                liveData.addRules.postValue(Resource.Error(message = "Bạn không có quyền thêm nội quy"))
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

        val rulesUpdate: ArrayList<Rules> = liveData.rules.value?.data as? ArrayList<Rules> ?: arrayListOf()

        val positionExits: Int = rulesUpdate.indexOfFirst { it.id == rule.id }
        if(isUpdate) {
            // update
            if(positionExits != -1) rulesUpdate[positionExits] = rule
            // add
            else rulesUpdate.add(rule.copy(boardingHouseId = userController.state.currentBoardingHouseId))
        }
        else {
            // delete
            if(positionExits != -1) rulesUpdate[positionExits] = rule.copy(status = QuyDinhEntity.STATUS_INACTIVE)
        }
        liveData.rules.postValue(Resource.Success(rulesUpdate))
        liveData.addRules.postValue(Resource.Success(rule))
    }

    fun saveRules(rules: List<Rules>){
        val currentUser = userController.state.currentUser.value?.data

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