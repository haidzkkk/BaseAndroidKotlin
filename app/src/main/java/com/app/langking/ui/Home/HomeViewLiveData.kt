package com.app.langking.ui.Home

import androidx.lifecycle.MutableLiveData
import com.app.langking.ultis.Resource
import com.app.langking.core.AppViewLiveData
import com.app.langking.data.model.Travle

open class HomeViewLiveData : AppViewLiveData {
     val travlesLiveData : MutableLiveData<Resource<List<Travle>>> = MutableLiveData()
     val travTest : MutableLiveData<String> = MutableLiveData()
}