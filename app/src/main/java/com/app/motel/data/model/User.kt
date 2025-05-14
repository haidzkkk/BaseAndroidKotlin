package com.history.vietnam.data.model

import com.app.motel.data.model.RealTimeId

data class User(
    override val id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val email: String? = null,
    val numberPhone: String? = null,
    val avatar: String? = null,
): RealTimeId {
    val getUserName get() =  if(name?.isNotEmpty() == true) name else username ?: "..."
}