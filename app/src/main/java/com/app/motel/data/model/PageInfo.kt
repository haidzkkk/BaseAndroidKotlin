package com.app.motel.data.model

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.app.motel.data.network.FirebaseManager
import com.app.motel.feature.historicalEvent.HistoricalEventActivity
import com.app.motel.feature.historicalFigure.HistoricalFigureActivity
import com.app.motel.feature.quiz.QuizActivity
import com.app.motel.feature.territory.TerritoryActivity
import com.google.gson.Gson
import com.history.vietnam.ultis.AppConstants
import com.history.vietnam.ultis.DateConverter
import com.history.vietnam.ultis.startActivityWithSlide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class PageInfo(
    val name: String? = null,
    val year: String? = null,
    val wikiPageId: String? = null,
    val firebaseId: String? = null,
    val firebasePath: String? = null,
    val info: Map<String, String?>? = null,
    val type: Type? = null,
    val time: String? = null,
    override val id: String? = firebaseId + type?.name,
): RealTimeId {
    var action: Action? = null
    var data: Map<String, String?>? = null

    fun startActivity(activity: FragmentActivity){
        when(type){
            Type.DYNASTY, Type.HISTORICAL_FIGURE -> {
                activity.startActivityWithSlide(Intent(activity, HistoricalFigureActivity::class.java).apply {
                    putExtra(HistoricalFigureActivity.ITEM_INFO_KEY, Gson().toJson(this@PageInfo))
                })
            }
            Type.HISTORICAL_EVENT -> {
                activity.startActivityWithSlide(Intent(activity, HistoricalEventActivity::class.java).apply {
                    putExtra(HistoricalEventActivity.ITEM_INFO_KEY, Gson().toJson(this@PageInfo))
                })
            }
            Type.TERRITORY, Type.TERRITORY_TIMELINE_ENTRY -> {
                activity.startActivityWithSlide(Intent(activity, TerritoryActivity::class.java).apply {
                    putExtra(TerritoryActivity.ITEM_INFO_KEY, Gson().toJson(this@PageInfo))
                })
            }
            Type.QUIZ -> {
                QuizActivity.startActivity(activity, firebaseId ?: "")
            }
            else -> {}
        }
    }

    companion object{
        fun getIdPageInfo(id: String?, type: Type): String = id + type.name

        fun from(figure: HistoricalFigure, dynastyId: String?): PageInfo {
            return PageInfo(
                name = figure.name,
                year = figure.birthYear,
                wikiPageId = figure.wikiPageId,
                firebaseId = figure.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_DYNASTY_PATH}/${dynastyId}/${AppConstants.FIREBASE_HISTORY_DYNASTY_FIGURE_NODE}/${figure.id}",
                type = Type.HISTORICAL_FIGURE,
                time = DateConverter.getCurrentStringDateTime(),
                info = mapOf(
                    "Sinh" to (figure.birthYear ?: ""),
                    "Mất" to (figure.deathDate ?: ""),
                    "Vợ" to (figure.spouse ?: ""),
                    "Tước hiệu" to (figure.title ?: ""),
                    "Triều đại" to (figure.dynasty ?: ""),
                ),
            )
        }
        fun from(event: HistoricalEvent): PageInfo {
            return PageInfo(
                name = event.name,
                year = event.birthYear,
                wikiPageId = event.wikiPageId,
                firebaseId = event.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_EVENT_PATH}/${event.id}",
                type = Type.HISTORICAL_EVENT,
                time = DateConverter.getCurrentStringDateTime(),
                info = mapOf(
                    "Thời gian" to (event.birthYear ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                    "Triều đại" to (event.dynasty ?: ""),
                ),
            )
        }
        fun from(dynasty: HistoryDynasty): PageInfo {
            return PageInfo(
                name = dynasty.name,
                year = "${dynasty.fromDate ?: "Không rõ"} - ${dynasty.toDate ?: "Không rõ"}",
                firebaseId = dynasty.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_DYNASTY_PATH}/${dynasty.id}",
                type = Type.DYNASTY,
                time = DateConverter.getCurrentStringDateTime(),
            )
        }

        fun from(territory: Territory): PageInfo {
            return PageInfo(
                name = territory.title,
                year = territory.period,
                firebaseId = territory.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_TERRITORY_PATH}/${territory.id}",
                type = Type.TERRITORY,
                time = DateConverter.getCurrentStringDateTime(),
            )
        }

        fun from(section: Section, territoryId: String): PageInfo {
            return PageInfo(
                name = section.content,
                year = section.title,
                firebaseId = section.id,
                firebasePath = "${AppConstants.FIREBASE_HISTORY_TERRITORY_PATH}/${territoryId}/${AppConstants.FIREBASE_TIMELINE_ENTRY_NODE}/${section.id}",
                type = Type.TERRITORY_TIMELINE_ENTRY,
                time = DateConverter.getCurrentStringDateTime(),
            )
        }

        fun from(quiz: Quiz): PageInfo {
            return PageInfo(
                name = quiz.title,
                year = quiz.period,
                firebaseId = quiz.id,
                firebasePath = "${AppConstants.FIREBASE_QUIZ_PATH}/${quiz.id}",
                type = Type.QUIZ,
                time = DateConverter.getCurrentStringDateTime(),
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
        DETAIL,
        LIKE,
        RANK,
        COMMENT,
    }
}