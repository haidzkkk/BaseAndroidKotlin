package com.example.travle.ui.Home

import com.example.travle.core.TravleViewEvents

sealed class HomeViewEvent : TravleViewEvents {
    object ReturnTestViewEvent : HomeViewEvent()
}