package com.app.langking.ui.Home

import com.app.langking.core.AppViewEvent

sealed class HomeViewEvent : AppViewEvent {
    object ReturnTestViewEvent : HomeViewEvent()
}