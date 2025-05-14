package com.app.motel.data.repository

import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class HistoricalEventRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {

    suspend fun getHistoryEvent() = firebaseManager.getList(AppConstants.FIREBASE_HISTORY_EVENT_PATH, HistoricalEvent::class.java)

}