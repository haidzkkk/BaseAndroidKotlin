package com.app.motel.data.model

data class PageInfo(
    val name: String?,
    val wikiPageId: String?,
    val firebaseId: String?,
    val firebasePath: String?,
    val info: Map<String, String?>?,
) {
}