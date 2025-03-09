package com.app.langking.data.model

data class Account(
    val id: Int = 0,
    var username: String = "",
    var password: String = "",
    var email: String = "",
    var fullName: String? = null,
    var avatar: String? = null
)
