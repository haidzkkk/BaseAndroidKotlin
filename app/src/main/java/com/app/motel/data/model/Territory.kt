package com.app.motel.data.model

data class Territory(
    override val id: Int? = null,
    val title: String? = null,
    val period: String? = null,
    val description: String? = null,
    val image: String? = null,
    val timelineEntries: List<Section>? = null,
): RealTimeId
