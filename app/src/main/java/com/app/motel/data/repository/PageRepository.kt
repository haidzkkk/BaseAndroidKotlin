package com.app.motel.data.repository

import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import javax.inject.Inject

class PageRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {
    suspend fun getFigureSummary(wikiId: String) = wikiApi.getSummary(wikiId)

    suspend fun getFigureDetail(pageId: Int) = wikiApi.getDetail(pageId)
}