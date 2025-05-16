package com.history.vietnam.feature.Home

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppState
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.model.Rooms
import com.history.vietnam.data.model.User

open class HomeViewLiveData : AppState {
    val searchInfo = MutableLiveData<Resource<List<PageInfo>>>()
}