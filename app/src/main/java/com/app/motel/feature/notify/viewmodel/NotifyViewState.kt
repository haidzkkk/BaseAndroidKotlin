package com.app.motel.feature.notify.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.entity.KhieuNaiEntity
import com.app.motel.data.model.Complaint
import com.app.motel.data.model.Resource

class NotifyViewState: AppViewLiveData {
    val complaints = MutableLiveData<List<Complaint>>()
    val currentType = MutableLiveData<KhieuNaiEntity.Type>(KhieuNaiEntity.Type.APPLICATION)

    val currentHandleComplaint = MutableLiveData<Complaint>()
    val updateComplaint = MutableLiveData<Resource<Complaint>>()

    val getNotifyAdmin: List<Complaint> get () = complaints.value?.filter {
        it.type == currentType.value?.value
    }?.reversed() ?: arrayListOf()
}