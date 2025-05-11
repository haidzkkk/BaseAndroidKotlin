package com.app.motel.data.model

data class HistoricalEvent(
    override val id: Int? = null,
    val name: String? = null,
    val birthYear: String? = null,
    val title: String? = null,
    val dynasty: String? = null,
    val description: String? = null,
    val image: String? = null,
    val wikiPageId: String? = null,
) : RealTimeId{
}