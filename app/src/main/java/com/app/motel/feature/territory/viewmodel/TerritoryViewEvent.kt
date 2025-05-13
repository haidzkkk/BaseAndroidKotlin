package com.app.motel.feature.territory.viewmodel

import com.app.motel.data.model.Territory
import com.history.vietnam.core.AppViewEvent

sealed class TerritoryViewEvent: AppViewEvent {
    data class SetCurrentTerritory(val territory: Territory): TerritoryViewEvent()
}