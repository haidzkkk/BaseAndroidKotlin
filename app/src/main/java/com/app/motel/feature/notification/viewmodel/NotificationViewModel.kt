package com.app.motel.feature.notification.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.AppNotification
import com.app.motel.data.model.HistoricalEvent
import com.app.motel.data.model.HistoricalFigure
import com.app.motel.data.model.Notification
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.PageInfo.Companion.from
import com.app.motel.data.model.Quiz
import com.app.motel.data.model.Territory
import com.app.motel.data.repository.HistoricalEventRepository
import com.app.motel.data.repository.HistoricalFigureRepository
import com.app.motel.data.repository.NotificationRepository
import com.app.motel.data.repository.QuizRepository
import com.app.motel.data.repository.TerritoryRepository
import com.app.motel.feature.profile.UserController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
    private val eventRepository: HistoricalEventRepository,
    private val historicalFigureRepository: HistoricalFigureRepository,
    private val quizRepository: QuizRepository,
    val userController: UserController,
): AppBaseViewModel<NotificationState, NotificationViewAction, NotificationViewEvent> (NotificationState()){
    override fun handle(action: NotificationViewAction) {
    }

    fun init(){
        clear()
        if(userController.state.getCurrentUser == null) return
        notificationRepository.addListenerNotification(userController.state.getCurrentUserId, notificationListener)
    }

    fun clear(){
        liveData.notifications.postValue(null)
        notificationRepository.removeNotification(userController.state.getCurrentUserId, notificationListener)
    }


    private val notificationListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val list = snapshot.children.mapNotNull { it.getValue(AppNotification::class.java) }
                viewModelScope.launch {
                    liveData.notifications.postValue(list)
                    Log.e("FirebaseManager", "<--- 200 comment: $list}")
                }
            } catch (e: Exception) {
                Log.e("FirebaseManager", "<--- 400 comment: ${e.message}")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseManager", "<-- ${error.code} comment: ${error.toException()}")
        }
    }

    fun setAllNotificationRead(){
        viewModelScope.launch {
            liveData.notifications.value?.forEach { notify ->
                if(notify.read == false){
                    notificationRepository.updateNotification(notify.copy(
                        read = true
                    ))
                }
            }
        }
    }

    fun updateNotificationRead(notification: AppNotification){
        if(notification.read == true) return
        viewModelScope.launch {
            notificationRepository.updateNotification(notification.copy(read = true))
        }
    }

    fun handleDataRedirect(notification: AppNotification){
        viewModelScope.launch {
            when(notification.type){
                AppNotification.Type.EVENT, AppNotification.Type.EVENT_LIKE, AppNotification.Type.EVENT_COMMENT -> {
                    val event: Resource<HistoricalEvent> = eventRepository.getEventByPath(notification.data?.get(AppNotification.objectPath) ?: "")
                    val pageInfo = event.data?.let { from(it) }?.apply {
                        data = notification.data

                        action = if(notification.type == AppNotification.Type.EVENT_LIKE
                            || notification.type == AppNotification.Type.EVENT_COMMENT)
                            PageInfo.Action.COMMENT else PageInfo.Action.DETAIL
                    }
                    liveData.redirectPageInfo.postValue(pageInfo)
                }
                AppNotification.Type.FIGURE, AppNotification.Type.FIGURE_LIKE, AppNotification.Type.FIGURE_COMMENT -> {
                    val figure: Resource<HistoricalFigure> = historicalFigureRepository.getFiguresByPath(notification.data?.get(AppNotification.objectPath) ?: "")
                    val pageInfo = figure.data?.let { from(it, null).copy(firebasePath = notification.data?.get(AppNotification.objectPath)) }?.apply {
                        data = notification.data
                        action = if(notification.type == AppNotification.Type.FIGURE_LIKE
                            || notification.type == AppNotification.Type.FIGURE_COMMENT)
                            PageInfo.Action.COMMENT else PageInfo.Action.DETAIL
                        action = PageInfo.Action.COMMENT
                    }
                    liveData.redirectPageInfo.postValue(pageInfo)
                }
                AppNotification.Type.TERRITORY, AppNotification.Type.TERRITORY_LIKE, AppNotification.Type.TERRITORY_COMMENT -> {
                    val pageInfo = from(Territory()).apply {
                        data = notification.data
                        action = if(notification.type == AppNotification.Type.TERRITORY_LIKE
                            || notification.type == AppNotification.Type.TERRITORY_COMMENT)
                            PageInfo.Action.COMMENT else PageInfo.Action.DETAIL
                    }
                    liveData.redirectPageInfo.postValue(pageInfo)
                }
                AppNotification.Type.QUIZ -> {
                    val quiz: Resource<Quiz> = quizRepository.getQuiz(notification.data?.get(AppNotification.focusId) ?: "")
                    val pageInfo = quiz.data?.let{ from(it) }?.apply {
                        data = notification.data
                        action = PageInfo.Action.DETAIL
                    }
                    liveData.redirectPageInfo.postValue(pageInfo)
                }
                else -> {

                }
            }
        }
    }
}