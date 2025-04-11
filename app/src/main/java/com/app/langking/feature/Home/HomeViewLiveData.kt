package com.app.langking.feature.Home

import androidx.lifecycle.MutableLiveData
import com.app.langking.ultis.Resource
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Account
import com.app.langking.data.model.Category

open class HomeViewLiveData : AppViewLiveData {
     val currentUser : MutableLiveData<Account?> = MutableLiveData()
     val categories : MutableLiveData<Resource<List<Category>>> = MutableLiveData()
}