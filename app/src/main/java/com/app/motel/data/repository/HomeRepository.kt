package com.history.vietnam.data.repository

import com.app.motel.data.network.DataSources
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.data.local.RoomDAO
import com.history.vietnam.data.network.ApiMock
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiMock,
    private val roomDAO: RoomDAO,
    private val firebaseManager: FirebaseManager
) {

    suspend fun pushEvent(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_EVENT_PATH, DataSources.historicalEvents())
    }

    suspend fun pushFigures(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_DYNASTY_PATH, DataSources.historicalDynasties())
    }
    suspend fun pushTerritory(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_TERRITORY_PATH, DataSources.historyTerritory())
    }
}