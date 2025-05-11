package com.history.vietnam.data.repository

import com.app.motel.data.network.Data
import com.app.motel.data.network.DatabasePath
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.data.local.RoomDAO
import com.history.vietnam.data.model.Rooms
import com.history.vietnam.data.model.User
import com.history.vietnam.data.network.ApiMock
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiMock,
    private val roomDAO: RoomDAO,
    private val firebaseManager: FirebaseManager
) {

    suspend fun pushEvent(){
        firebaseManager.push(DatabasePath.HISTORY_EVENT.dbPath(), Data.historicalEvents())
    }

    suspend fun pushFigures(){
        firebaseManager.push(DatabasePath.HISTORY_DYNASTY.dbPath(), Data.historicalDynasties())
    }
}