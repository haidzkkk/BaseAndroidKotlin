package com.app.motel.data.repository

import android.content.SharedPreferences
import com.app.motel.common.AppConstants
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.local.UserDAO
import com.app.motel.data.model.CommonUser
import com.app.motel.data.model.Tenant
import com.app.motel.data.model.User
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val prefs: SharedPreferences,
    private val userDAO: UserDAO,
    private val tenantDAO: TenantDAO,
) {
    fun saveCurrentUser(user: CommonUser){
        prefs.edit().putString(AppConstants.USER_ID_KEY, user.id).apply()
    }

    fun removeCurrentUser(){
        prefs.edit().remove(AppConstants.USER_ID_KEY).apply()
    }

    fun getCurrentUserId(): String {
        return prefs.getString(AppConstants.USER_ID_KEY, "") ?: ""
    }

    suspend fun getCurrentUser(): CommonUser? {
        val userId = prefs.getString(AppConstants.USER_ID_KEY, null)
        if(userId.isNullOrBlank()) return null

        val admin = userDAO.getById(userId)?.toModel()
        val user = tenantDAO.getById(userId)?.toModel()

        if(admin != null) return CommonUser.AdminUser(admin)
        else if(user != null) return CommonUser.NormalUser(user)
        return  null
    }

}