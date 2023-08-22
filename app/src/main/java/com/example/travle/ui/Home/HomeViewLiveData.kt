package com.example.travle.ui.Home

import androidx.lifecycle.MutableLiveData
import com.example.preproject.utils.Resource
import com.example.travle.core.TravleViewLiveData
import com.example.travle.data.model.Travle

open class HomeViewLiveData : TravleViewLiveData {
     val travlesLiveData : MutableLiveData<Resource<List<Travle>>> = MutableLiveData()
     val travTest : MutableLiveData<String> = MutableLiveData()
}