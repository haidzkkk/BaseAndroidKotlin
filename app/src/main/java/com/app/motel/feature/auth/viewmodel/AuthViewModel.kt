package com.app.motel.feature.auth.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.data.repository.AuthRepository
import com.app.motel.data.repository.UserRepository
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
): AppBaseViewModel<AuthState, AuthViewAction, AuthViewEvent>(AuthState()) {
    override fun handle(action: AuthViewAction) {
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            liveData.login.postValue(Resource.Loading())
            try {
                val userLogin = authRepository.loginUser(username, password)
                liveData.login.postValue(userLogin)

                if(userLogin.isSuccess() && userLogin.data != null){
                    userRepository.saveCurrentUser(userLogin.data)
                }
            }catch (e: Exception){
                liveData.login.postValue(Resource.Error(message = e.toString()))
            }
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            liveData.register.postValue(Resource.Loading())
            try {
                val userRegister = authRepository.registerUser(username, password)
                liveData.register.postValue(userRegister)

                if(userRegister.isSuccess() && userRegister.data != null){
                    userRepository.saveCurrentUser(userRegister.data)
                }
            }catch (e: Exception){
                liveData.register.postValue(Resource.Error(message = e.toString()))
            }
        }
    }
}