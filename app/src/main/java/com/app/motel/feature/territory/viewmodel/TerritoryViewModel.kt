package com.app.motel.feature.territory.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.app.motel.data.model.AppNotification
import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.Territory
import com.app.motel.data.repository.NotificationRepository
import com.app.motel.data.repository.TerritoryRepository
import com.app.motel.data.repository.UserRepository
import com.app.motel.feature.profile.UserController
import com.app.motel.ultis.IDManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.core.AppBaseViewModel
import com.history.vietnam.data.model.Comment
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.AppConstants
import com.history.vietnam.ultis.DateConverter
import kotlinx.coroutines.launch
import javax.inject.Inject

class TerritoryViewModel @Inject constructor(
    private val repository: TerritoryRepository,
    private val userRepository: UserRepository,
    private val notificationRepository: NotificationRepository,
    val userController: UserController,
): AppBaseViewModel<TerritoryState, TerritoryViewAction, TerritoryViewEvent>(TerritoryState()) {
    override fun handle(action: TerritoryViewAction) {

    }

    fun setInfoSelect(infoSelect: PageInfo?){
        liveData.infoSelect.value = infoSelect
    }

    fun getTerritories() {
        viewModelScope.launch {
            val historyFigures = repository.getTerritories()
            liveData.territories.postValue(historyFigures.data)
        }

        repository.addListenerComment(commentListener)
    }

    fun findTerritoryIndexFromFlatPosition(flatPosition: Int): Int {
        val list = liveData.territories.value ?: return -1
        var currentFlatIndex = 0

        list.forEachIndexed { index, territory ->
            if (currentFlatIndex == flatPosition) return index
            currentFlatIndex++

            val childCount = territory.timelineEntries?.size ?: 0
            if (flatPosition < currentFlatIndex + childCount) {
                return index
            }
            currentFlatIndex += childCount
        }
        return -1
    }

    fun postCurrentEvent(territory: Territory){
        _viewEvents.post(TerritoryViewEvent.SetCurrentTerritory(territory))
    }

    private val commentListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val list = snapshot.children.mapNotNull { it.getValue(Comment::class.java) }
                viewModelScope.launch {
                    for (comment in list) {
                        comment.user = userRepository.getUserById(comment.idUser)
                        comment.comments?.values?.toList()?.forEach {
                            it.user = userRepository.getUserById(it.idUser)
                        }
                    }
                    liveData.comments.postValue(Resource.Success(list))
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

    fun likeComment(comment: Comment) {
        if(userController.state.getCurrentUser == null) {
            userController.state.loginUser.postValue(true)
            return
        }

        if(comment.likes == null) comment.likes = hashMapOf()

        val isLike = comment.likes!![userController.state.getCurrentUserId] == null
        if(!isLike){
            comment.likes!!.remove(userController.state.getCurrentUserId)
        }else{
            comment.likes!![userController.state.getCurrentUserId] = userController.state.getCurrentUserId
        }

        liveData.comments.postValue(Resource.Success(liveData.comments.value?.data ?: listOf()))

        viewModelScope.launch {
            repository.likeComment(comment)
        }

        if(isLike
            && userController.state.getCurrentUserId.isNotBlank()
            && comment.idUser != userController.state.getCurrentUserId){

            sendNotificationLikeComment(
                comment = comment,
                senderId = userController.state.getCurrentUserId,
                receiverId = comment.idUser ?: "",
                payload = mapOf(
                    AppNotification.focusId to comment.id,
                    AppNotification.focusParentId to comment.parentCommentId,
                    AppNotification.objectPath to AppConstants.FIREBASE_HISTORY_TERRITORY_PATH,
                )
            )
        }
    }

    fun replyComment(comment: Comment?) {
        liveData.currentCommentReply.postValue(comment)
    }

    fun sendComment(content: String, commentReply: Comment?):Boolean {
        if(userController.state.getCurrentUser == null) {
            userController.state.loginUser.postValue(true)
            return false
        }

        val comment = Comment(
            id = IDManager.createID(),
            content = content,
            time = DateConverter.getCurrentStringDateTime(),
            idUser = userController.state.getCurrentUserId,
            parentCommentId = liveData.currentCommentReply.value.let { it?.parentCommentId ?: it?.id },
        ).apply {
            user = userController.state.getCurrentUser
            isUploading = true
        }

        val comments = liveData.comments.value?.data as? ArrayList<Comment> ?: arrayListOf()

        if(comment.parentCommentId != null){
            val parentComment = comments.find { it.id == comment.parentCommentId }
            if(parentComment?.comments == null) parentComment?.comments = hashMapOf()
            parentComment?.comments?.apply {
                this[comment.id ?: ""] = comment
            }
        }else{
            comments.add(0, comment)
        }

        liveData.comments.postValue(Resource.Success(comments))
        replyComment(null)

        viewModelScope.launch {
            repository.sendComment(comment)
        }

        val isReply = comment.parentCommentId != null

        if(isReply && commentReply != null &&
            userController.state.getCurrentUserId.isNotBlank()
            && commentReply.idUser != userController.state.getCurrentUserId){

            sendNotificationComment(
                comment = comment,
                senderId = userController.state.getCurrentUserId,
                receiverId = commentReply.idUser ?: "",
                payload = mapOf(
                    AppNotification.focusId to comment.id,
                    AppNotification.focusParentId to comment.parentCommentId,
                    AppNotification.objectPath to AppConstants.FIREBASE_HISTORY_TERRITORY_PATH,
                )
            )
        }

        return true
    }

    private fun sendNotificationComment(
        comment: Comment,
        senderId: String,
        receiverId: String,
        payload: Map<String, String?>? = null
    ){
        viewModelScope.launch {
            val sender = userController.state.getCurrentUser

            val notification = AppNotification(
                id = IDManager.createID(),
                title = "Thông báo",
                message = "${sender?.name ?: "Người dùng"} đã trả lời luận của bạn: ${comment.content}",
                type = AppNotification.Type.TERRITORY_COMMENT,
                senderId = senderId,
                receiverId = receiverId,
                time = DateConverter.getCurrentStringDateTime(),
                read = false,
                data = payload
            )

            notificationRepository.addNotification(notification)
            notificationRepository.sendNotification(notification)
        }
    }

    private fun sendNotificationLikeComment(
        comment: Comment,
        senderId: String,
        receiverId: String,
        payload: Map<String, String?>? = null
    ){
        viewModelScope.launch {
            val sender = userController.state.getCurrentUser

            val notification = AppNotification(
                id = IDManager.createID(),
                title = "Thông báo",
                message = "${sender?.name ?: "Người dùng"} đã thích bình luận của bạn: ${comment.content}",
                type = AppNotification.Type.TERRITORY_LIKE,
                senderId = senderId,
                receiverId = receiverId,
                time = DateConverter.getCurrentStringDateTime(),
                read = false,
                data = payload
            )

            notificationRepository.addNotification(notification)
            notificationRepository.sendNotification(notification)
        }
    }
}