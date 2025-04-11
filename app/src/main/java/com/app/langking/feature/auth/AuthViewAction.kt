package com.app.langking.feature.auth

import com.app.langking.core.AppViewActions
import com.app.langking.data.model.Account

open class AuthViewAction : AppViewActions {
    data class SignupPreviousPageViewAction(var page: Int) : AuthViewAction()
    data class SignupForwardPageViewAction(var page: Int) : AuthViewAction()
    data class RegisterSetupViewAction(var account: Account) : AuthViewAction()
    data class RegisterViewAction(var account: Account) : AuthViewAction()
    data class LoginViewAction(var usernameOrEmail: String, var password: String) : AuthViewAction()
}