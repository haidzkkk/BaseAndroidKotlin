package com.app.motel.data.repository

import com.app.motel.data.local.RoomDAO
import javax.inject.Inject

class CreateContractRepository @Inject constructor(
    private val roomDAO: RoomDAO,
) {
}