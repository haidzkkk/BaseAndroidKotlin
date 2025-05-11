package com.history.vietnam.feature.Home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.repository.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

    init {
        setupData()
    }

    override fun handle(action: HomeViewAction) {
        when(action){
            is HomeViewAction.getMotelViewAction -> {}
            else -> {}
        }
    }

    fun sendEventTest() {
        _viewEvents.post(HomeViewEvent.ReturnTestViewEvent)
    }

    private fun setupData() {
        viewModelScope.launch {
//            repo.pushFigures()
//            repo.pushEvent()
        }
    }
}