package com.app.motel.ultis

import java.util.UUID

object IDManager {
    fun createID(): String = UUID.randomUUID().toString()
}