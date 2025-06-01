package com.app.langking.feature.auth

import androidx.lifecycle.viewModelScope
import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.model.Account
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.repository.AuthRepository
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepository: UserRepository,
) : AppBaseViewModel<AuthViewState, AuthViewAction, AuthViewEvent>(AuthViewState()) {

    override fun handle(action: AuthViewAction) {
        when(action){
            is AuthViewAction.SignupPreviousPageViewAction -> liveData.signupPageIndex.value = action.page
            is AuthViewAction.SignupForwardPageViewAction -> liveData.signupPageIndex.value = action.page
            is AuthViewAction.RegisterViewAction -> register(action.account)
            is AuthViewAction.RegisterSetupViewAction -> liveData.registerAccount = action.account
            is AuthViewAction.LoginViewAction -> login(action.usernameOrEmail, action.password)
        }
    }

    private fun register(account: Account){
        liveData.registerResult.postValue(Resource.Loading())
        if(account.username.isEmpty()){
            liveData.registerResult.postValue(Resource.Error(false, "Email không được để trống"))
            return
        }else if (account.password.isEmpty()){
            liveData.registerResult.postValue(Resource.Error(false, "Mật khẩu không được để trống"))
            return
        }

        viewModelScope.launch {
            val result = authRepo.registerAccount(account.copy(id = account.username))
            if(result.isSuccess()){
                liveData.registerResult.postValue(Resource.Success(true))
                if(result.data != null) userRepository.setCurrentUser(result.data)
                liveData.registerAccount = null
            }else{
                liveData.registerResult.postValue(Resource.Error(false, result.message ?: "Đăng ký thất bại"))
            }
        }
    }

    private fun login(usernameOrEmail: String, password: String){
        liveData.loginResult.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = authRepo.login(usernameOrEmail, password)
            if(result.data != null) userRepository.setCurrentUser(result.data)
            liveData.loginResult.postValue(result)
        }
    }

}