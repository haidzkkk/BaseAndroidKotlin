package com.app.motel.feature.notification.viewmodel

import androidx.lifecycle.MutableLiveData
import com.app.motel.data.model.AppNotification
import com.app.motel.data.model.PageInfo
import com.history.vietnam.core.AppState

class NotificationState: AppState {
    val getCountNotificationUnread: Int get() = (notifications.value?.count { it.read == false } ?: 0).let {
        if(it > 99) 99 else it
    }

    val notifications = MutableLiveData<List<AppNotification>>()
    val redirectPageInfo = MutableLiveData<PageInfo>()
}