package com.app.langking.feature.Home

import com.app.langking.core.AppViewActions

sealed class HomeViewAction : AppViewActions {
    object updateViewAction : HomeViewAction()
    object getCategoryViewAction : HomeViewAction()
}