package com.history.vietnam.feature.Home

import com.history.vietnam.core.AppViewEvent

sealed class HomeViewEvent : AppViewEvent {
    object ReturnTestViewEvent : HomeViewEvent()
}