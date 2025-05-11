package com.app.motel.feature.historicalEvent.viewmodel

import com.app.motel.data.model.HistoricalEvent
import com.history.vietnam.core.AppViewEvent

sealed class HistoricalEventViewEvent: AppViewEvent {
    data class SetCurrentEvent(val historicalEvent: HistoricalEvent): HistoricalEventViewEvent()
}