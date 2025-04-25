package com.app.motel.feature.tenant.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.motel.common.ultis.containsSearch
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.entity.NguoiThueEntity
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Tenant

class TenantState: AppViewLiveData {
    val filterState = MutableLiveData<NguoiThueEntity.Status>(NguoiThueEntity.Status.ACTIVE)
    val searchText = MutableLiveData<String>("")
    val tenants = MutableLiveData<Resource<List<Tenant>>>()

    val currentTenant = MutableLiveData<Tenant>()
    val handleTenant = MutableLiveData<Resource<Tenant>>()

    val getListTenantByStateSearch: List<Tenant> get() = (tenants.value?.data ?: arrayListOf()).filter{
        it.status == filterState.value?.value
        && (it.fullName.containsSearch(searchText.value ?: "")
         || it.phoneNumber?.containsSearch(searchText.value ?: "") == true)
    }
}