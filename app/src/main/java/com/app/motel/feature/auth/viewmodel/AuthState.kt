package com.app.motel.feature.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User

class AuthState: AppState {
    val login = MutableLiveData<Resource<User>>(Resource.Initialize())
    val register = MutableLiveData<Resource<User>>(Resource.Initialize())
}