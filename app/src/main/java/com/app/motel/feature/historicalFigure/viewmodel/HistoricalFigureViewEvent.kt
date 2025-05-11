package com.app.motel.feature.historicalFigure.viewmodel

import com.app.motel.data.model.HistoryDynasty
import com.history.vietnam.core.AppViewEvent

sealed class HistoricalFigureViewEvent: AppViewEvent {
    data class SetCurrentDynasty(val historyDynasty: HistoryDynasty): HistoricalFigureViewEvent()
}