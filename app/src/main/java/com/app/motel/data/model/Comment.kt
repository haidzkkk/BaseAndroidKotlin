package com.history.vietnam.data.model

import com.app.motel.data.model.RealTimeId
import com.app.motel.ultis.IDManager
import com.google.firebase.database.Exclude

data class Comment(
    override val id: String? = IDManager.createID(),
    val idUser: String? = null,
    val content: String? = null,
    val time: String? = null,
    val parentCommentId: String? = null,
    var comments: HashMap<String, Comment>? = null,
    var likes: HashMap<String, String>? = null,
): RealTimeId {
    @get:Exclude
    var user: User? = null

    @get:Exclude
    var isUploading: Boolean? = null
}