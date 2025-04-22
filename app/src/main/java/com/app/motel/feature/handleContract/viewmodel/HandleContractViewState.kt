package com.app.motel.feature.handleContract.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.Contract
import com.app.motel.data.model.Resource

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