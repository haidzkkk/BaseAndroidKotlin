package com.app.langking.data.repository

import android.content.SharedPreferences
import com.app.langking.data.local.AccountDAO
import com.app.langking.data.model.Account
import com.app.langking.data.network.FirebaseManager
import com.app.langking.ultis.AppConstants
import com.app.langking.ultis.Resource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
) {

    suspend fun registerAccount(account: Account): Resource<Account>{
        if(account.id.isEmpty() || account.id != account.username){
            return Resource.Error(null, "Tài khoản không tồn tại")
        }

        val existingUserByUsername = firebaseManager.getObject("${AppConstants.FIREBASE_USER_NODE}/${account.id}", Account::class.java)
        if(existingUserByUsername.data != null){
            return Resource.Error(null, "Username đã tồn tại")
        }
        return firebaseManager.push("${AppConstants.FIREBASE_USER_NODE}/${account.id}", account)
    }

    suspend fun login(username: String, password: String): Resource<Account>{
        val existingUserByUsername = firebaseManager.getObject("${AppConstants.FIREBASE_USER_NODE}/$username", Account::class.java)
        if(existingUserByUsername.isError()){
            return Resource.Error(null, "Username không tồn tại")
        }
        if(existingUserByUsername.data?.password != password){
            return Resource.Error(null, "Mật khẩu không đúng")
        }
        return Resource.Success(existingUserByUsername.data)
    }
}