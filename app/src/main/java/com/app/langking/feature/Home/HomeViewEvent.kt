package com.app.langking.feature.Home

import com.app.langking.core.AppViewEvent

sealed class HomeViewEvent : AppViewEvent {
    object ReturnTestViewEvent : HomeViewEvent()
}