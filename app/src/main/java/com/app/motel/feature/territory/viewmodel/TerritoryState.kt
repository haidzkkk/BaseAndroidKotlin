package com.app.motel.feature.territory.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Territory
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Comment
import com.history.vietnam.data.model.Resource

class TerritoryState: AppState {
    val territories = MutableLiveData<List<Territory>?>()
    val comments = MutableLiveData<Resource<List<Comment>>>()

    val currentCommentReply = MutableLiveData<Comment?>()
    var selectContent = MutableLiveData<Int>(0)
}