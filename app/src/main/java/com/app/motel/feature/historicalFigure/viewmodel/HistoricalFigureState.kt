package com.app.motel.feature.historicalFigure.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppState

class HistoricalFigureState: AppState {
    val isSelectFigure get() = infoSelect.value?.action != PageInfo.Action.TIME_LINE && infoSelect.value?.type == PageInfo.Type.HISTORICAL_FIGURE
    val getSelectInfoId get() = infoSelect.value?.firebaseId
    val getSelectTimeLineId get() = infoSelect.value?.firebasePath?.let {
        val dynastyId = it.split("/").getOrNull(1)
        dynastyId
    }

    val infoSelect = MutableLiveData<PageInfo?>()
    val currentFigure = MutableLiveData<HistoricalFigure?>(null)
    val currentDynasty = MutableLiveData<HistoryDynasty?>(null)

    val historyDynasty = MutableLiveData<List<HistoryDynasty>?>()

}