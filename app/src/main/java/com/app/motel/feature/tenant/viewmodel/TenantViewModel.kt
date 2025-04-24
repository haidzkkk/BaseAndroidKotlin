package com.app.motel.feature.tenant.viewmodel

import com.app.motel.core.AppBaseViewModel
import com.app.motel.data.repository.TenantRepository
import com.app.motel.feature.profile.ProfileController
import javax.inject.Inject

class TenantViewModel @Inject constructor(
    tenantRepository: TenantRepository,
    profileController: ProfileController
): AppBaseViewModel<TenantState, TenantViewAction, TenantViewEvent>(TenantState()) {
    override fun handle(action: TenantViewAction) {

    }


}