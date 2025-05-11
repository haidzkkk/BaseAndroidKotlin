package com.app.motel.feature.historicalFigure.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Section
import com.app.motel.data.model.WikiDetail
import com.app.motel.data.model.WikiSummary
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource

class HistoricalFigureState: AppState {
    val historyDynasty = MutableLiveData<List<HistoryDynasty>?>()

}