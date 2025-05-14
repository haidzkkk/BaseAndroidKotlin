package com.app.motel.data.repository

import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class HistoricalFigureRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {

    suspend fun getHistoryFigures() = firebaseManager.getList(AppConstants.FIREBASE_HISTORY_DYNASTY_PATH, HistoryDynasty::class.java)

}