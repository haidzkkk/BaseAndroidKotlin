package com.app.motel.data.repository

import com.app.motel.data.model.Territory
import com.app.motel.data.network.FirebaseManager
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.data.model.Comment
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class TerritoryRepository @Inject constructor(
    private val firebaseManager: FirebaseManager
) {
    suspend fun getTerritories() = firebaseManager.getList(AppConstants.FIREBASE_HISTORY_TERRITORY_PATH, Territory::class.java)

    fun addListenerComment(listener: ValueEventListener) =
        firebaseManager.addListener(AppConstants.FIREBASE_COMMENT_TERRITORY_PATH, listener)

    fun removeListenerComment(listener: ValueEventListener) =
        firebaseManager.removeListener(AppConstants.FIREBASE_COMMENT_TERRITORY_PATH, listener)

    suspend fun likeComment(comment: Comment){
        val commentPath = if(comment.parentCommentId != null){
            "${AppConstants.FIREBASE_COMMENT_TERRITORY_PATH}/${comment.parentCommentId}/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}/${AppConstants.FIREBASE_LIKE_NODE}"
        }else{
            "${AppConstants.FIREBASE_COMMENT_TERRITORY_PATH}/${comment.id}/${AppConstants.FIREBASE_LIKE_NODE}"
        }

        firebaseManager.overwrite(commentPath, comment.likes)
    }

    suspend fun sendComment(comment: Comment) {
        val commentPath = if(comment.parentCommentId != null){
            "${AppConstants.FIREBASE_COMMENT_TERRITORY_PATH}/${comment.parentCommentId}/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}"
        }else{
            "${AppConstants.FIREBASE_COMMENT_TERRITORY_PATH}/${comment.id}"
        }
        firebaseManager.push(commentPath, comment)
    }
}