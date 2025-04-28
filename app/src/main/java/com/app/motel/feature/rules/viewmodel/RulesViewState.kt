package com.app.motel.feature.rules.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.entity.QuyDinhEntity
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Rules

class RulesViewState: AppViewLiveData {
    val boardingHouseRules = MutableLiveData<Resource<List<BoardingHouse>>>()

    val addRules = MutableLiveData<Resource<Rules>>()
    val saveRules = MutableLiveData<Resource<Boolean>>()

    val getAllRulesActiveFromBoardingHouse get () = boardingHouseRules.value?.data?.flatMap { it.rules ?: arrayListOf() }?.filter { it.status == QuyDinhEntity.STATUS_ACTIVE } ?: arrayListOf()
    val getAllRulesFromBoardingHouse get () = boardingHouseRules.value?.data?.flatMap { it.rules ?: arrayListOf() } ?: arrayListOf()
}