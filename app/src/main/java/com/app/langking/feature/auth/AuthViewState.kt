package com.app.langking.feature.auth

import androidx.lifecycle.MutableLiveData
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Account
import com.app.langking.ultis.Resource

open class AuthViewState : AppViewLiveData {
    val signupPageIndex : MutableLiveData<Int> = MutableLiveData()
    val registerResult : MutableLiveData<Resource<Boolean>> = MutableLiveData<Resource<Boolean>>()
    val loginResult : MutableLiveData<Resource<Account>> = MutableLiveData<Resource<Account>>()
    var registerAccount : Account? = null
}
