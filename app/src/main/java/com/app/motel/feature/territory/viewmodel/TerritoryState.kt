package com.app.motel.feature.territory.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Territory
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Comment
import com.history.vietnam.data.model.Resource

class TerritoryState: AppState {
    val territories = MutableLiveData<List<Territory>?>()
    val comments = MutableLiveData<Resource<List<Comment>>>()

    val currentCommentReply = MutableLiveData<Comment?>()
    var selectContent = MutableLiveData<Int>(0)

    val infoSelect = MutableLiveData<PageInfo?>()
    val getTerritoryInfoSelectIndex get() = infoSelect.value?.let {
        var position: Int? = null
        val territoryId = getTerritoryInfoSelectId
        if(territoryId != null){
            territories.value?.forEachIndexed { index, territory ->
                if (territory.id == territoryId) {
                    position = index
                    return@forEachIndexed
                }
            }
        }
        position
    }
    val getTerritoryInfoSelectId get() = infoSelect.value?.let {
        val nodes = it.firebasePath?.split("/") ?: return@let null
        if(it.type == PageInfo.Type.TERRITORY || it.type == PageInfo.Type.TERRITORY_TIMELINE_ENTRY) {
            nodes.getOrNull(1)
        }else null
    }
    val getTimeLineInfoSelectId get() = infoSelect.value?.let {
        val nodes = it.firebasePath?.split("/") ?: return@let null
        if(it.type == PageInfo.Type.TERRITORY_TIMELINE_ENTRY) {
            nodes.lastOrNull()
        }else null
    }
}