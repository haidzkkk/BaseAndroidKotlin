package com.app.motel.feature.profile

import com.app.motel.data.network.FirebaseManager
import com.app.motel.data.repository.UserRepository
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserController @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val repo: UserRepository,
) {
    val state: UserState = UserState()

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

    fun clearData() {
        state.updateCurrentUser.postValue(Resource.Initialize())
    }

    fun destroyController() {
        job.cancel()
    }

    fun updateInformation(name: String, email: String, numberPhone: String) {
        state.updateCurrentUser.postValue(Resource.Loading())
        scope.launch {
            if(state.currentUser.value?.data?.id == null){
                state.updateCurrentUser.postValue(Resource.Error(message = "Không tìm thấy người dùng"))
                return@launch
            }
            val updateUser = repo.updateCurrentUser(
                state.currentUser.value!!.data!!.copy(
                    name = name,
                    email = email,
                    numberPhone = numberPhone
                )
            )

            if(updateUser.isSuccess() && updateUser.data != null){
                state.currentUser.postValue(updateUser)
            }
            state.updateCurrentUser.postValue(updateUser)
        }
    }
}

