package com.example.travle.ui.Home

import com.example.travle.TravleViewActions

sealed class HomeViewAction : TravleViewActions {
    object getTravleViewAction : HomeViewAction()
}