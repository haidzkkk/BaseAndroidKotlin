package com.app.motel.data.repository

import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Territory
import com.app.motel.data.network.DatabasePath
import com.app.motel.data.network.FirebaseManager
import javax.inject.Inject

class TerritoryRepository @Inject constructor(
    private val firebaseManager: FirebaseManager
) {
    suspend fun getTerritories() = firebaseManager.getList(DatabasePath.HISTORY_TERRITORY.dbPath(), Territory::class.java)

}