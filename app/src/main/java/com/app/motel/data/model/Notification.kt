package com.app.motel.data.model

data class AppNotification(
    val id: String? = null,
    val title: String? = null,
    val message: String? = null,
    val type: Type? = null,
    val senderId: String? = null,
    val receiverId: String? = null,
    val time: String? = null,
    val read: Boolean? = false,
    val data: Map<String, String?>? = null
){
    companion object{
        val focusId = "focusId"
        val objectPath = "objectPath"
        val focusParentId = "focusParentId"
    }

    enum class Type(){
        QUIZ,
        EVENT,
        EVENT_LIKE,
        EVENT_COMMENT,
        FIGURE,
        FIGURE_LIKE,
        FIGURE_COMMENT,
        TERRITORY,
        TERRITORY_LIKE,
        TERRITORY_COMMENT,
        SYSTEM;
    }
}