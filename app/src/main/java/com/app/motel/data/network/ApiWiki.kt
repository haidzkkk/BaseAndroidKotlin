package com.app.motel.data.network

import com.app.motel.data.model.WikiDetail
import com.app.motel.data.model.WikiSummary
import com.history.vietnam.data.model.Rooms
import com.history.vietnam.ultis.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiWiki {

    @GET(AppConstants.WIKI_GET_SUMMARY + "/{wikiId}")
    suspend fun getSummary(
        @Path("wikiId") pageId: String
    ): WikiSummary

    @GET(AppConstants.WIKI_GET_DETAIL)
    suspend fun getDetail(
        @Query("pageids") pageId: Int,
        @Query("action") action: String = "query",
        @Query("prop") prop: String = "extracts",
        @Query("explaintext") explainText: Boolean = false,
        @Query("format") exIntro: String = "json",

    ): WikiDetail
}