package com.app.motel.data.repository

import com.app.motel.data.entity.HopDongEntity
import com.app.motel.data.entity.KhuTroEntity
import com.app.motel.data.local.BoardingHouseDAO
import com.app.motel.data.local.ContractDAO
import com.app.motel.data.local.RoomDAO
import com.app.motel.data.local.TenantDAO
import com.app.motel.data.model.BoardingHouse
import com.app.motel.data.model.Contract
import javax.inject.Inject

class HandleContractRepository @Inject constructor(
    private val boardingHouseDAO: BoardingHouseDAO,
    private val roomDAO: RoomDAO,
    private val contractDAO: ContractDAO,
    private val tenantDAO: TenantDAO,
) {
}