package com.app.motel.data.repository

import com.app.motel.data.entity.NguoiDungEntity
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.local.UserDAO
import com.app.motel.data.model.CommonUser
import com.app.motel.data.model.Resource
import com.app.motel.data.model.User
import com.app.motel.data.network.ApiMock
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: ApiMock,
    private val userDAO: UserDAO,
    private val tenantDAO: TenantDAO,
) {

    suspend fun register(user: User): Resource<CommonUser> {
        val userEntity = user.toEntityRegister()
        return try {
            if (userDAO.getUserByUsername(userEntity.tenDangNhap) != null) {
                Resource.Error(message = "Tên đăng nhập đã tồn tại")
            } else if(userEntity.email == null || userDAO.getUserByEmail(userEntity.email) != null){
                Resource.Error(message = "Email đã tồn tại")
            } else {
                userDAO.insertUser(userEntity)
                Resource.Success(CommonUser.AdminUser(userEntity.toModel()))
            }
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }

    suspend fun login(username: String, password: String): Resource<CommonUser> {
        return try {
            val admin = userDAO.getUserByUsername(username)
            val user = tenantDAO.getUserByUsername(username)

            if (admin == null && user == null) {
                Resource.Error(message = "Tài khoản không tồn tại")
            } else if (admin?.matKhau != password && user?.matKhau != password) {
                Resource.Error(message = "Sai mật khẩu")
            } else if (admin?.trangThai == NguoiDungEntity.STATE_INACTIVE) {
                Resource.Error(message = "Tài khoản đang bị khóa")
            } else {
                Resource.Success(
                    if (admin != null) CommonUser.AdminUser(admin.toModel())
                    else if (user != null) CommonUser.NormalUser(user.toModel())
                    else null
                )
            }
        } catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }
}