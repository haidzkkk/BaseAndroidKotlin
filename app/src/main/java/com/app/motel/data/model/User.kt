package com.app.motel.data.model

data class ser(
    val id: String,
    val username: String,
    val password: String,
    val name: String,
    val avatar: Int,
    val follow: ArrayList<Rooms>,
) {
}