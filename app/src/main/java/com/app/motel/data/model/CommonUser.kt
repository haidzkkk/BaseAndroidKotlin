package com.app.motel.data.model

sealed class CommonUser {
    abstract val id: String
    abstract val name: String
    abstract val role: Role

    val isAdmin get() = role == Role.admin

    data class AdminUser(val admin: User): CommonUser() {
        override val id = admin.id
        override val name = admin.fullName
        override val role = Role.admin
    }

    data class NormalUser(val user: Tenant): CommonUser() {
        override val id = user.id
        override val name = user.fullName
        override val role = Role.user
    }
}
