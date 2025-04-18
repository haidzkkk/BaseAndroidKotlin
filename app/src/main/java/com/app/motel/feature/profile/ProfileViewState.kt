package com.app.motel.feature.profile

import androidx.lifecycle.MutableLiveData
import com.app.motel.core.AppViewLiveData
import com.app.motel.data.model.CommonUser
import com.app.motel.data.model.Resource
import com.app.motel.data.model.User

class ProfileViewState : AppViewLiveData {
    val currentUser = MutableLiveData<Resource<CommonUser>>()
    val currentUserId get() = currentUser.value?.data?.id ?: ""
}