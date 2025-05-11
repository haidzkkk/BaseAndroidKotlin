package com.app.motel.feature.historicalFigure.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Section
import com.app.motel.data.repository.HistoricalFigureRepository
import com.app.motel.feature.setting.SettingController
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoricalFigureViewModel @Inject constructor(
    private val historicalFigureRepository: HistoricalFigureRepository,
    val settingRepository: SettingController,
): AppBaseViewModel<HistoricalFigureState, HistoricalFigureViewAction, HistoricalFigureViewEvent>(HistoricalFigureState()) {
    override fun handle(action: HistoricalFigureViewAction) {

    }

    fun getHistoryFigure() {
        viewModelScope.launch {
            val historyFigures = historicalFigureRepository.getHistoryFigures()
            liveData.historyDynasty.postValue(historyFigures.data)
        }
    }

    fun postCurrentDynasty(historyDynasty: HistoryDynasty){
        _viewEvents.post(HistoricalFigureViewEvent.SetCurrentDynasty(historyDynasty))
    }
}