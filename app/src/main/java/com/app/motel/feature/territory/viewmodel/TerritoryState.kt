package com.app.motel.feature.territory.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Territory
import com.history.vietnam.core.AppState

class TerritoryState: AppState {
    val territories = MutableLiveData<List<Territory>?>()

    var selectContent = MutableLiveData<Int>(0)
}