package com.app.motel.feature.Home

import com.app.motel.core.AppViewEvent

sealed class HomeViewEvent : AppViewEvent {
    object ReturnTestViewEvent : HomeViewEvent()
}