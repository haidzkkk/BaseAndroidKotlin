package com.app.motel.feature.profile

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User

class UserState : AppState {
    val getCurrentUser: User? get() = currentUser.value?.data
    val getCurrentUserId: String get() = currentUser.value?.data?.id ?: ""
    val currentSavePages: Map<String, PageInfo> get() = getCurrentUser?.saves ?: mapOf()
    val isLogin: Boolean get() = getCurrentUser != null
    fun checkIsSaved(id: String?, type: PageInfo.Type): Boolean? = getCurrentUser?.checkIsSaved(id, type)

    val currentUser = MutableLiveData<Resource<User>>()

    val updateCurrentUser = MutableLiveData<Resource<User>>()

    val loginUser = MutableLiveData<Boolean>()
}