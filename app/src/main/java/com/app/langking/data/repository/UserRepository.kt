package com.app.langking.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.local.DatabaseManager
import com.app.langking.data.model.Account
import com.app.langking.data.model.Category
import com.app.langking.data.network.FirebaseManager
import com.app.langking.ultis.AppConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseManager: FirebaseManager,
) {
    private var currentUser: Account? = null
    private var accountId: String? = null
    val getCurrentUser: Account
        get() {
            if(currentUser == null){
                return Account.fromDefault()
            }
            return currentUser!!
        }


    fun setCurrentUser(user: Account) {
        currentUser = user
        accountId = user.id
        sharedPreferences.edit().putString(AppConstants.currentUserKey, user.id).apply()
    }

    fun getCurrentUserId(): String {
        if (accountId == null) {
            accountId = sharedPreferences.getString(AppConstants.currentUserKey, Account.ACCOUNT_DEFAULT_ID)
        }
        return accountId ?: Account.ACCOUNT_DEFAULT_ID
    }

    suspend fun getCurrentUser(): Account {
        if(currentUser == null){
            val userId = sharedPreferences.getString(AppConstants.currentUserKey, Account.ACCOUNT_DEFAULT_ID)
            if(userId != Account.ACCOUNT_DEFAULT_ID){
                currentUser = firebaseManager.getObject("${AppConstants.FIREBASE_USER_NODE}/$userId", Account::class.java).data
            }else{
                currentUser = Account.fromDefault()
            }
            accountId = currentUser?.id ?: Account.ACCOUNT_DEFAULT_ID
        }
        return currentUser ?: Account.fromDefault()
    }

    fun logout() {
        currentUser = Account.fromDefault()
        accountId = Account.ACCOUNT_DEFAULT_ID
        sharedPreferences.edit().remove(AppConstants.currentUserKey).apply()
    }
}