package com.app.langking.data.repository

import android.content.SharedPreferences
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

    fun getCurrentUser(): Account? {
        if(currentUser == null){
            val userId = sharedPreferences.getInt(AppConstants.currentUserKey, -1)
            if(userId == -1) return null
            currentUser = accountRepo.getAccountById(userId)
        }
        return currentUser
    }
}