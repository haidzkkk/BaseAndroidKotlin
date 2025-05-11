package com.app.motel.data.model

data class HistoryDynasty(
    override val id: Int? = null,
    val name: String? = null,
    val fromDate: String? = null,
    val toDate: String? = null,
    val figures: List<HistoricalFigure>? = null,
) : RealTimeId{
}