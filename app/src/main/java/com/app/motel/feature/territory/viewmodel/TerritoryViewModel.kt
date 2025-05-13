package com.app.motel.feature.territory.viewmodel

import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.Territory
import com.app.motel.data.repository.TerritoryRepository
import com.history.vietnam.core.AppBaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TerritoryViewModel @Inject constructor(
    private val repository: TerritoryRepository
): AppBaseViewModel<TerritoryState, TerritoryViewAction, TerritoryViewEvent>(TerritoryState()) {
    override fun handle(action: TerritoryViewAction) {

    }

    fun getTerritories() {
        viewModelScope.launch {
            val historyFigures = repository.getTerritories()
            liveData.territories.postValue(historyFigures.data)
        }
    }

    fun findTerritoryIndexFromFlatPosition(flatPosition: Int): Int {
        val list = liveData.territories.value ?: return -1
        var currentFlatIndex = 0

        list.forEachIndexed { index, territory ->
            if (currentFlatIndex == flatPosition) return index
            currentFlatIndex++

            val childCount = territory.timelineEntries?.size ?: 0
            if (flatPosition < currentFlatIndex + childCount) {
                return index
            }
            currentFlatIndex += childCount
        }
        return -1
    }

    fun postCurrentEvent(territory: Territory){
        _viewEvents.post(TerritoryViewEvent.SetCurrentTerritory(territory))
    }
}