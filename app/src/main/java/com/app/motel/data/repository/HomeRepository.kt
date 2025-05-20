package com.history.vietnam.data.repository

import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoryDynasty
import com.app.motel.data.model.Territory
import com.app.motel.data.network.DataSources
import com.app.motel.data.network.FirebaseManager
import com.history.vietnam.data.local.RoomDAO
import com.history.vietnam.data.model.Resource
import com.history.vietnam.data.network.ApiMock
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiMock,
    private val roomDAO: RoomDAO,
    private val firebaseManager: FirebaseManager
) {

    suspend fun pushEvent(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_EVENT_PATH, DataSources.historicalEvents())
    }

    suspend fun pushFigures(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_DYNASTY_PATH, DataSources.historicalDynasties())
    }
    suspend fun pushTerritory(){
        firebaseManager.push(AppConstants.FIREBASE_HISTORY_TERRITORY_PATH, DataSources.historyTerritory())
    }
    suspend fun pushQuiz(){
        firebaseManager.push(AppConstants.FIREBASE_QUIZ_PATH, DataSources.quizzes())
    }

    suspend fun getEvents(): Resource<List<HistoricalEvent>>{
        return firebaseManager.getList(AppConstants.FIREBASE_HISTORY_EVENT_PATH, HistoricalEvent::class.java)
    }

    suspend fun getTerritory(): Resource<List<Territory>>{
        return firebaseManager.getList(AppConstants.FIREBASE_HISTORY_TERRITORY_PATH, Territory::class.java)
    }

    suspend fun getDynasty(): Resource<List<HistoryDynasty>>{
        return firebaseManager.getList(AppConstants.FIREBASE_HISTORY_DYNASTY_PATH, HistoryDynasty::class.java)
    }
}