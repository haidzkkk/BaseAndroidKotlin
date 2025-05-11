package com.app.motel.data.model

import com.google.gson.annotations.SerializedName

data class WikiDetail(
    @SerializedName("batchcomplete") val batchComplete: String,
    @SerializedName("query") val query: Query
)

data class Query(
    @SerializedName("pages") val pages: Map<String, Page>
)

data class Page(
    @SerializedName("pageid") val pageId: Int,
    @SerializedName("ns") val ns: Int,
    @SerializedName("title") val title: String,
    @SerializedName("extract") val extract: String
)
