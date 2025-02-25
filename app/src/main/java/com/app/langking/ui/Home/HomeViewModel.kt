package com.app.langking.ui.Home

import android.annotation.SuppressLint
import com.app.langking.core.AppBaseViewModel
import com.app.langking.data.repository.HomeRepository
import com.app.langking.ultis.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>() {

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
            .subscribe(
                {result ->  _liveData.travlesLiveData.postValue(Resource.Success(result))},
                {error ->}
            )
    }
}