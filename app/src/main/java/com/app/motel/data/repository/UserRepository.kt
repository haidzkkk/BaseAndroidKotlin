package com.app.motel.data.repository

import android.content.SharedPreferences
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val prefs: SharedPreferences,
    private val firebaseManager: FirebaseManager,
) {

    fun saveCurrentUser(user: User){
        prefs.edit().putString(AppConstants.SHARED_PREFERENCES_USER_ID_KEY_KEY, user.id).apply()
    }

    fun removeCurrentUser(){
        prefs.edit().remove(AppConstants.SHARED_PREFERENCES_USER_ID_KEY_KEY).apply()
    }

    fun getCurrentUserId(): String {
        return prefs.getString(AppConstants.SHARED_PREFERENCES_USER_ID_KEY_KEY, "") ?: ""
    }

    suspend fun getCurrentUser(): User? {
        val userId = prefs.getString(AppConstants.SHARED_PREFERENCES_USER_ID_KEY_KEY, null)
        if(userId.isNullOrBlank()) return null

        val user = firebaseManager.getObject("${AppConstants.FIREBASE_USER_PATH}/$userId", User::class.java)

        if(!user.isSuccess() || user.data == null) return null
        return user.data
    }

    suspend fun updateCurrentUser(user: User): Resource<User> {
        try {
            if(user.id == null) {
                return Resource.Error(message = "User id is null")
            }
            return firebaseManager.overwrite("${AppConstants.FIREBASE_USER_PATH}/${user.id}", user)
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "Unknown error")
        }
        return Resource.Error(message = "Unknown error")
    }

    suspend fun getUserById(id: String?): User?{
        if(id.isNullOrEmpty()) return null
        return firebaseManager.getObject("${AppConstants.FIREBASE_USER_PATH}/$id", User::class.java).data
    }
}