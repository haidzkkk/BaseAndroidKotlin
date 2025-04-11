package com.app.langking.data.model

data class Account(
    val id: Int = 0,
    var username: String = "",
    var password: String = "",
    var email: String = "",
    var fullName: String? = null,
    var avatar: String? = null
){
    val isLogin: Boolean
        get () = id != -1

    companion object{
        fun fromDefault(): Account{
            return Account(
                id = -1,
                username = "",
                password = "",
                email = "",
            )
        }
    }
}
