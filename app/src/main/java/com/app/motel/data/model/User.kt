package com.history.vietnam.data.model

import com.app.motel.data.model.PageInfo
import com.app.motel.data.model.RealTimeId

data class User(
    override val id: String? = null,
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val email: String? = null,
    val numberPhone: String? = null,
    val avatar: String? = null,
    val saves: Map<String, PageInfo>? = null,
): RealTimeId {
    val getUserName get() =  if(name?.isNotEmpty() == true) name else username ?: "..."

    fun checkIsSaved(id: String?, type: PageInfo.Type) = saves?.get(PageInfo.getIdPageInfo(id, type)) != null
}