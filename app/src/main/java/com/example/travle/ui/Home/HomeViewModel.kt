package com.example.travle.ui.Home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.preproject.utils.Resource
import com.example.travle.core.TravleBaseViewModel
import com.example.travle.data.repository.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
    ) : TravleBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>() {

    init {
        test()
    }

    override fun handle(action: HomeViewAction) {
        when(action){
            is HomeViewAction.getTravleViewAction -> getTravle()
        }
    }

    fun handleRetrunTest() {
        _viewEvents.post(HomeViewEvent.ReturnTestViewEvent)
    }

    fun test() = _liveData.travTest.postValue("test viewModel: ${Random.nextInt()}")

    @SuppressLint("CheckResult")
    fun getTravle() {
        _liveData.travlesLiveData.postValue(Resource.Loading(null))
        repo.getTravle().observeOn(AndroidSchedulers.mainThread())
            .subscribe{travles ->
                _liveData.travlesLiveData.postValue(Resource.Success(travles))
            }
    }
}