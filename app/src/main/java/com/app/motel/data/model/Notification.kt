package com.app.motel.data.model

data class AppNotification(
    val id: String = "",
    val title: String = "",
    val message: String = "",
    val type: String = "",
    val senderId: String? = null,
    val receiverId: String? = null,
    val imageUrl: String? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val read: Boolean = false,
    val data: Map<String, String>? = null
){

    enum class Type(val value: String){
        QUIZ("quiz"),
        EVENT("event"),
        COMMENT("comment"),
        LIKE("like"),
        SYSTEM("system");

        companion object {
            fun fromValue(value: String): Type? {
                return entries.find { it.value == value }
            }
        }
    }
}