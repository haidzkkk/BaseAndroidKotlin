package com.app.motel.feature.historicalEvent.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.repository.HistoricalEventRepository
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.core.AppBaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoricalEventViewModel @Inject constructor(
    private val historicalEventRepository: HistoricalEventRepository,
    val settingRepository: SettingController,
): AppBaseViewModel<HistoricalEventState, HistoricalEventViewAction, HistoricalEventViewEvent>(HistoricalEventState()) {
    override fun handle(action: HistoricalEventViewAction) {
    }

    fun getHistoryEvent() {
        viewModelScope.launch {
            val historyFigures = historicalEventRepository.getHistoryEvent()
            liveData.historyEvents.postValue(historyFigures.data)
        }
    }

    fun postCurrentEvent(historicalEvent: HistoricalEvent){
        _viewEvents.post(HistoricalEventViewEvent.SetCurrentEvent(historicalEvent))
    }
}