package com.app.motel.feature.notify.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.Complaint
import com.app.motel.data.model.Notification
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room

class NotifyViewState: AppViewLiveData {
    val complaints = MutableLiveData<List<Complaint>>()

    val addNews = MutableLiveData<Resource<Notification>>()
}