package com.app.motel.feature.HandleContract.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant

class HandleContractViewState: AppViewLiveData {
    val contracts = MutableLiveData<Resource<List<Contract>>>()
    val currentStateContract = MutableLiveData<Contract.State>()
    val updateContract = MutableLiveData<Resource<Contract>>()

    val getContractToState: List<Contract>
        get() = contracts.value?.data?.filter {
            // state UNKNOWN set to ENDED
            if(it.state == Contract.State.UNKNOWN
                && currentStateContract.value == Contract.State.ENDED) true

            else it.state == currentStateContract.value
        } ?: arrayListOf()
}