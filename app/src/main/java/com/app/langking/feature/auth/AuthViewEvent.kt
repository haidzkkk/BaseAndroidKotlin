package com.app.langking.feature.auth

import com.app.langking.core.AppViewEvent

sealed class AuthViewEvent : AppViewEvent {
//    object ChangePageEvent : AuthEvent()
    data class ChangePageEvent(val pageIndex: Int) : AuthViewEvent()
}