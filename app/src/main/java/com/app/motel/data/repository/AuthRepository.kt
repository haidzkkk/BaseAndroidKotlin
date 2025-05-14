package com.app.motel.data.repository

import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.User
import com.history.vietnam.ultis.AppConstants
import java.security.MessageDigest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseManager: FirebaseManager
) {

    suspend fun loginUser(username: String, password: String): Resource<User> {
        if(username.isEmpty() || password.isEmpty()){
            return Resource.Error("Vui lòng nhập đầy đủ thông tin đăng nhập!")
        }

        val user = firebaseManager.getObject("${AppConstants.FIREBASE_USER_PATH}/${username}", User::class.java)
        if(!user.isSuccess()){
            return Resource.Error("Đã có lỗi: ${user.message}")
        }else if(user.data == null){
            return Resource.Error("Tài khoản của bạn không tồn tại!")
        }else if(user.data.password != hashPassword(password)){
            return Resource.Error("Mật khẩu không chính xác!")
        }
        return user
    }

    suspend fun registerUser(username: String, password: String): Resource<User> {
        if(username.isEmpty() || password.isEmpty()){
            return Resource.Error("Vui lòng nhập đầy đủ thông tin đăng ký!")
        }

        val user = User(
            id = username,
            username = username,
            password = hashPassword(password),
        )

        val userExists = firebaseManager.getObject("${AppConstants.FIREBASE_USER_PATH}/${user.id}", User::class.java)
        if(userExists.isSuccess() && userExists.data != null){
            return Resource.Error("Tên đăng nhập đã tồn tại")
        }

        return firebaseManager.push("${AppConstants.FIREBASE_USER_PATH}/${user.id}", user)
    }

    private fun hashPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}