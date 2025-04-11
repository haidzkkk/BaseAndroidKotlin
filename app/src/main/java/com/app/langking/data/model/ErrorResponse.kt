package com.app.langking.data.model

data class Error(
    val code: Int? = null,
    val message: String? = null,
    val status: String? = null
)

data class ErrorResponse(
    val error: Error? = null
)