package com.app.motel.feature.Home

import com.app.motel.core.AppViewActions

sealed class HomeViewAction : AppViewActions {
    object getMotelViewAction : HomeViewAction()
}