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
    ) : AppBaseViewModel<HomeViewLiveData, HomeViewAction, HomeViewEvent>(HomeViewLiveData()) {

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

    fun test() = liveData.travTest.postValue("test viewModel: ${Random.nextInt()}")

    @SuppressLint("CheckResult")
    fun getTravle() {
        liveData.travlesLiveData.postValue(Resource.Loading(null))
        repo.getTravle().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result ->  liveData.travlesLiveData.postValue(Resource.Success(result))},
                {error ->}
            )
    }
}