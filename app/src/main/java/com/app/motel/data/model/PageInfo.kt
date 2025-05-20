package com.app.motel.data.model

import com.history.vietnam.ultis.AppConstants

data class PageInfo(
    val name: String? = null,
    val year: String? = null,
    val wikiPageId: String? = null,
    val firebaseId: String? = null,
    val firebasePath: String? = null,
    val info: Map<String, String?>? = null,
    val type: Type? = null,
) {
    var action: Action? = null

    companion object{
        fun fromHistoricalFigure(figure: HistoricalFigure, dynastyId: String? = null): PageInfo {
            return PageInfo(
                name = figure.name,
                year = figure.birthYear,
                wikiPageId = figure.wikiPageId,
                firebaseId = figure.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_DYNASTY_PATH}/${dynastyId}/${AppConstants.FIREBASE_HISTORY_DYNASTY_FIGURE_NODE}/${figure.id}",
                type = Type.HISTORICAL_FIGURE,
                info = mapOf(
                    "Sinh" to (figure.birthYear ?: ""),
                    "Mất" to (figure.deathDate ?: ""),
                    "Vợ" to (figure.spouse ?: ""),
                    "Tước hiệu" to (figure.title ?: ""),
                    "Triều đại" to (figure.dynasty ?: ""),
                ),
            )
        }
        fun fromHistoricalEvent(event: HistoricalEvent): PageInfo {
            return PageInfo(
                name = event.name,
                year = event.birthYear,
                wikiPageId = event.wikiPageId,
                firebaseId = event.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_EVENT_PATH}/${event.id}",
                type = Type.HISTORICAL_EVENT,
                info = mapOf(
                    "Thời gian" to (event.birthYear ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                ),
            )
        }
        fun fromHistoryDynasty(dynasty: HistoryDynasty): PageInfo {
            return PageInfo(
                name = dynasty.name,
                year = "${dynasty.fromDate ?: "Không rõ"} - ${dynasty.toDate ?: "Không rõ"}",
                firebaseId = dynasty.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_DYNASTY_PATH}/${dynasty.id}",
                type = Type.DYNASTY,
            )
        }

        fun fromTerritory(territory: Territory): PageInfo {
            return PageInfo(
                name = territory.title,
                year = territory.period,
                firebaseId = territory.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_TERRITORY_PATH}/${territory.id}",
                type = Type.TERRITORY,
            )
        }

        fun fromSection(section: Section, territoryId: String): PageInfo {
            return PageInfo(
                name = section.content,
                year = section.title,
                firebaseId = section.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_TERRITORY_PATH}/${territoryId}/${AppConstants.FIREBASE_TIMELINE_ENTRY_NODE}/${section.id}",
                type = Type.TERRITORY_TIMELINE_ENTRY,
            )
        }

        fun fromQuiz(quiz: Quiz): PageInfo {
            return PageInfo(
                name = quiz.title,
                year = quiz.period,
                firebaseId = quiz.id,
                firebasePath = "${AppConstants.FIREBASE_QUIZ_PATH}/${quiz.id}",
                type = Type.QUIZ,
            )
        }
    }

    enum class Type{
        HISTORICAL_FIGURE,
        HISTORICAL_EVENT,
        TERRITORY,
        TERRITORY_TIMELINE_ENTRY,
        QUIZ,
        DYNASTY
    }

    enum class Action{
        TIME_LINE,
        DETAIL
    }
}