package com.history.vietnam.feature.Home

import com.history.vietnam.core.AppViewActions

sealed class HomeViewAction : AppViewActions {
    object getMotelViewAction : HomeViewAction()
}