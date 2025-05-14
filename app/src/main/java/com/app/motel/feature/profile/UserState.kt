package com.app.motel.feature.profile

import androidx.lifecycle.MutableLiveData
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User

class UserState : AppState {
    val getCurrentUser: User? get() = currentUser.value?.data
    val getCurrentUserId: String get() = currentUser.value?.data?.id ?: ""

    val currentUser = MutableLiveData<Resource<User>>()

    val updateCurrentUser = MutableLiveData<Resource<User>>()

    val loginUser = MutableLiveData<Boolean>()
}