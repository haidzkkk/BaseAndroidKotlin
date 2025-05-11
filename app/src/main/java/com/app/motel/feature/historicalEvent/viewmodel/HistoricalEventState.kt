package com.app.motel.feature.historicalEvent.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoryDynasty
import com.history.vietnam.core.AppState

class HistoricalEventState: AppState {
    val historyEvents = MutableLiveData<List<HistoricalEvent>?>()
}