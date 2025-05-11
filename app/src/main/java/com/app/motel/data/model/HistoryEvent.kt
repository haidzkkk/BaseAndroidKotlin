package com.app.motel.data.model

import java.util.Locale
import kotlin.random.Random

data class HistoricalEvent(
    override val id: Int? = null,
    val name: String? = null,
    val birthYear: String? = null,
    val title: String? = null,
    val dynasty: String? = null,
    val description: String? = null,
    val image: String? = null,
    val wikiPageId: String? = null,
    val level: Int? = 2,
) : RealTimeId{
    val getLevel get() = Level.fromValue(level)
    enum class Level(val value: Int){
        IMPORTANT(1),
        SUB_IMPORTANT(2),
        NORMAL(3);

        companion object{
            fun fromValue(level: Int?): Level = Level.entries.find { it.value == level } ?: NORMAL
        }
    }
}