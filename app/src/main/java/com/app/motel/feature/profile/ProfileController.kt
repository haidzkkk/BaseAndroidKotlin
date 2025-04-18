package com.app.motel.feature.profile

import com.app.motel.data.model.Resource
import com.app.motel.data.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileController @Inject constructor(
    private val repo: ProfileRepository
) {
    val state: ProfileViewState = ProfileViewState()

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun getCurrentUser(){
        scope.launch {
            val user = repo.getCurrentUser()
            if(user == null){
                state.currentUser.postValue(Resource.Error(message = "Không tìm thấy người dùng"))
            }else{
                state.currentUser.postValue(Resource.Success(user))
            }
        }
    }

    fun logout(){
        scope.launch {
            repo.removeCurrentUser()
            state.currentUser.postValue(Resource.Initialize())
        }
    }

    fun clearCoroutine() {
        job.cancel()
    }
}