package com.app.motel.data.repository

import android.content.SharedPreferences
import com.app.motel.data.local.RegulationDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.local.UserDAO
import javax.inject.Inject

class RegulationRepository @Inject constructor(
    private val regulationDAO: RegulationDAO,
) {

}