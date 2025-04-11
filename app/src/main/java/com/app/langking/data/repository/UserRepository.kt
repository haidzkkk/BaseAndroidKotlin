package com.app.langking.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.model.Account
import com.app.langking.ultis.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val accountRepo: AccountDAO,
) {
    private var currentUser: Account? = null

    fun setCurrentUser(user: Account) {
        currentUser = user
        sharedPreferences.edit().putInt(AppConstants.currentUserKey, user.id).apply()
    }

    fun getCurrentUser(): Account {
        if(currentUser == null){
            val userId = sharedPreferences.getInt(AppConstants.currentUserKey, -1)
            currentUser = if(userId != -1) (accountRepo.getAccountById(userId) ?: Account.fromDefault()) else Account.fromDefault()
        }
        return currentUser ?: Account.fromDefault()
    }

    fun logout() {
        currentUser = null
        sharedPreferences.edit().remove(AppConstants.currentUserKey).apply()
        getCurrentUser()
    }
}