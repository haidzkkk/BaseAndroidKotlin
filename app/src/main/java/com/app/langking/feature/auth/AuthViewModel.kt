package com.app.langking.feature.auth

import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.model.Account
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.repository.UserRepository
import com.app.langking.ultis.Resource
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val accountRepo: AccountDAO,
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
        if(account.email.isEmpty()){
            liveData.registerResult.postValue(Resource.Error(false, "Email không được để trống"))
            return
        }else if (account.password.isEmpty()){
            liveData.registerResult.postValue(Resource.Error(false, "Mật khẩu không được để trống"))
            return
        }

        liveData.registerResult.postValue(Resource.Loading())
        val existingUserByUsername = accountRepo.getAccountByUsername(account.username)
        val existingUserByEmail = accountRepo.getAccountByEmail(account.email)

        if (existingUserByUsername != null || existingUserByEmail != null) {
            liveData.registerResult.postValue(Resource.Error(false, "Username hoặc Email đã tồn tại"))
            return
        }

        val userId = accountRepo.insertAccount(account)
        val isSuccess = userId != -1L;
        if(isSuccess){
            liveData.registerResult.postValue(Resource.Success(true))

            val currentAccount: Account? = accountRepo.getAccountById(userId.toInt())
            if(currentAccount != null) userRepository.setCurrentUser(currentAccount)
            liveData.registerAccount = null
        }else{
            liveData.registerResult.postValue(Resource.Error(false, "Đăng ký thất bại"))
        }
    }

    private fun login(usernameOrEmail: String, password: String){
        liveData.loginResult.postValue(Resource.Loading())
        val user = accountRepo.getAccountByUsername(usernameOrEmail)
            ?: accountRepo.getAccountByEmail(usernameOrEmail)

        if (user != null && password == user.password) {
            userRepository.setCurrentUser(user)
            liveData.loginResult.postValue(Resource.Success(user))
        } else {
            liveData.loginResult.postValue(Resource.Error(null, "Sai thông tin đăng nhập"))
        }
    }

}