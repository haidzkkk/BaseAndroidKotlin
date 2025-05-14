package com.app.motel.data.repository

import com.app.motel.data.network.ApiWiki
import com.app.motel.data.network.FirebaseManager
import com.google.firebase.database.ValueEventListener
import com.history.vietnam.data.model.Comment
import com.history.vietnam.ultis.AppConstants
import javax.inject.Inject

class PageRepository @Inject constructor(
    private val firebaseManager: FirebaseManager,
    private val wikiApi: ApiWiki,
) {
    suspend fun getFigureSummary(wikiId: String) = wikiApi.getSummary(wikiId)

    suspend fun getFigureDetail(pageId: Int) = wikiApi.getDetail(pageId)

    fun addListenerComment(objectPath: String, listener: ValueEventListener) =
        firebaseManager.addListener("$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}", listener)

    fun removeListenerComment(objectPath: String, listener: ValueEventListener) =
        firebaseManager.removeListener("$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}", listener)

    suspend fun likeComment(objectPath: String, comment: Comment){
        val commentPath = if(comment.parentCommentId != null){
            "$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.parentCommentId}/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}/${AppConstants.FIREBASE_LIKE_NODE}"
        }else{
            "$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}/${AppConstants.FIREBASE_LIKE_NODE}"
        }

        firebaseManager.overwrite(commentPath, comment.likes)
    }

    suspend fun sendComment(objectPath: String, comment: Comment) {
        val commentPath = if(comment.parentCommentId != null){
            "$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.parentCommentId}/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}"
        }else{
            "$objectPath/${AppConstants.FIREBASE_COMMENT_NODE}/${comment.id}"
        }
        firebaseManager.push(commentPath, comment)
    }
}