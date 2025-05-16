package com.app.motel.feature.historicalEvent.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppState

class HistoricalEventState: AppState {
    val isSelectInfoDetail get() = infoSelect.value?.action == PageInfo.Action.DETAIL && infoSelect.value?.type == PageInfo.Type.HISTORICAL_EVENT
    val getSelectTimeLine get() = infoSelect.value?.wikiPageId

    val infoSelect = MutableLiveData<PageInfo?>()

    val historyEvents = MutableLiveData<List<HistoricalEvent>?>()
}