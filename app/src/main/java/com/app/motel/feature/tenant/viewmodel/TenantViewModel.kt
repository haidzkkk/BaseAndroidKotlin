package com.app.motel.feature.tenant.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.entity.NguoiThueEntity
import com.app.motel.data.model.Resource
import com.app.motel.data.model.Room
import com.app.motel.data.model.Tenant
import com.app.motel.data.repository.TenantRepository
import com.app.motel.feature.profile.ProfileController
import kotlinx.coroutines.launch
import javax.inject.Inject

class TenantViewModel @Inject constructor(
    private val tenantRepository: TenantRepository,
    private val profileController: ProfileController
): AppBaseViewModel<TenantState, TenantViewAction, TenantViewEvent>(TenantState()) {
    override fun handle(action: TenantViewAction) {

    }

    fun getTenants(){
        viewModelScope.launch {
            val tenants = tenantRepository.getTenants()
            liveData.tenants.postValue(tenants)
        }
    }

    fun initForm(tenant: Tenant?){
        liveData.currentTenant.postValue(tenant)

    }

    fun handleTenant(
        tenant: Tenant?,
        fullName: String?,
        state: String?,
        phoneNumber: String?,
        birthDay: String?,
        idCard: String?,
        homeTown: String?,
        username: String?,
        password: String?,
    ){
        liveData.handleTenant.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            fullName.isNullOrBlank() -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Họ tên là bắt buộc"))
                return
            }
            state.isNullOrBlank() -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Trạng thái là bắt buộc"))
                return
            }
            username.isNullOrBlank() -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Tên đăng nhập là bắt buộc"))
                return
            }
            password.isNullOrBlank() -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Mật khẩu là bắt buộc"))
                return
            }
        }

        viewModelScope.launch {
            val isUpdate = tenant != null
            val tenantUpdate: Tenant = if(isUpdate) tenant!!.copy(
                fullName = fullName!!,
                status = state,
                phoneNumber = phoneNumber,
                birthDay = birthDay,
                idCard = idCard,
                homeTown = homeTown,
                username = username!!,
                password = password!!,
            )else Tenant(
                fullName = fullName!!,
                status = state,
                phoneNumber = phoneNumber,
                birthDay = birthDay,
                idCard = idCard,
                homeTown = homeTown,
                username = username!!,
                password = password!!,
            )

            val result = if(isUpdate) tenantRepository.updateTenant(tenantUpdate)
                else tenantRepository.addTenant(tenantUpdate)

            liveData.handleTenant.postValue(result)
        }
    }

    fun changeStateTenant(
        tenant: Tenant?,
        isLock: Boolean,
    ){
        liveData.handleTenant.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
            tenant?.id == null -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Người thuê không tồn tại"))
                return
            }
        }

        viewModelScope.launch {
            val tenantUpdate: Tenant = tenant!!.copy(
                status = if(isLock) NguoiThueEntity.Status.TEMPORARY_ABSENT.value
                    else if(tenant.roomId != null) NguoiThueEntity.Status.ACTIVE.value
                    else NguoiThueEntity.Status.INACTIVE.value,
            )

            val result = tenantRepository.updateTenant(tenantUpdate)
            liveData.handleTenant.postValue(result.apply {
                message = if(isLock) "Khóa người thuê thành công" else "Mở khóa người thuê thành công"
            })
        }
    }

    fun updateTenantRent(tenant: Tenant, room: Room?){
        liveData.handleTenant.postValue(Resource.Loading())
        val currentUser = profileController.state.currentUser.value?.data
        when {
            currentUser == null || !currentUser.isAdmin -> {
                liveData.handleTenant.postValue(Resource.Error(message = "Bạn không có quyền tạo"))
                return
            }
        }
        viewModelScope.launch {
            val result = tenantRepository.updateUserRented(tenant, room?.id)
            liveData.handleTenant.postValue(result)
        }
    }

}