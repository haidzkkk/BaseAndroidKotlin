package com.app.langking.data.model

data class Account(
    var username: String = "",
    var password: String = "",
    var email: String = "",
    var fullName: String? = null,
    var avatar: String? = null,
    val role: String? = "USER",
    override val id: String = username,
): RealTimeId{
    val isLogin: Boolean
        get () = id != ACCOUNT_DEFAULT_ID

    companion object{
        const val ACCOUNT_DEFAULT_ID = "-1"

        fun fromDefault(): Account{
            return Account(
                id = ACCOUNT_DEFAULT_ID,
                username = "",
                password = "",
                email = "",
            )
        }
    }
}
