package com.app.langking.ultis

import java.util.UUID

object IDManager {
    fun createID(): String = UUID.randomUUID().toString()
}