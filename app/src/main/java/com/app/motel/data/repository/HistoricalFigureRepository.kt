package com.app.motel.data.repository

import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.DatabasePath
import com.app.motel.data.network.FirebaseManager
import javax.inject.Inject

class HistoricalFigureRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {

    suspend fun getHistoryFigures() = firebaseManager.getList(DatabasePath.HISTORY_DYNASTY.dbPath(), HistoryDynasty::class.java)

    suspend fun getFigureSummary(wikiId: String) = wikiApi.getSummary(wikiId)

    suspend fun getFigureDetail(pageId: Int) = wikiApi.getDetail(pageId)
}