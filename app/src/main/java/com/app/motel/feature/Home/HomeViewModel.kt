package com.app.motel.feature.Home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.model.Resource
import com.app.motel.data.repository.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
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
            is HomeViewAction.getMotelViewAction -> getMotel()
        }
    }

    fun sendEventTest() {
        _viewEvents.post(HomeViewEvent.ReturnTestViewEvent)
    }

    private fun test() = liveData.testString.postValue("test viewModel: ${Random.nextInt()}")

    private fun getMotel() {
        liveData.motelsLiveData.postValue(Resource.Loading(null))
        viewModelScope.launch {
            try {
                val response = repo.getRoomsNetwork()
                Log.e("TAG", "getMotel: $response", )
                liveData.motelsLiveData.postValue(Resource.Success(response))
                repo.setRoomToLocal(response)

            } catch (e: HttpException) {
                getRoomsLocal("HTTP error getMotel ${e.code()}")
            } catch (e: IOException) {
                getRoomsLocal("IOException error getMotel ${e.message}")
            } catch (e: Exception) {
                getRoomsLocal("Exception error getMotel ${e.message}")
            }
        }
    }

    private fun getRoomsLocal(message: String){
        viewModelScope.launch {
            try {
                val response = repo.getRoomsLocal()
                liveData.motelsLiveData.postValue(Resource.Success(response))
            } catch (e: Exception) {
                Log.e("TAG", "getRoomsLocal: $message", )
                liveData.motelsLiveData.postValue(Resource.Error(message = message))
            }
        }
    }
}