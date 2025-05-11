package com.history.vietnam.data.model

data class User(
    val id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val avatar: Int? = null,
    val follow: ArrayList<Rooms>? = null,
) {
}