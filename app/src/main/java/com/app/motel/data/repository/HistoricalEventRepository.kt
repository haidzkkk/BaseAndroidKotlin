package com.app.motel.data.repository

import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.DatabasePath
import com.app.motel.data.network.FirebaseManager
import javax.inject.Inject

class HistoricalEventRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {

    suspend fun getHistoryEvent() = firebaseManager.getList(DatabasePath.HISTORY_EVENT.dbPath(), HistoricalEvent::class.java)

}