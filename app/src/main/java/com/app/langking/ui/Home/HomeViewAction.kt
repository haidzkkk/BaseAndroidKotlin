package com.app.langking.ui.Home

import com.app.langking.core.AppViewActions

sealed class HomeViewAction : AppViewActions {
    object getTravleViewAction : HomeViewAction()
}