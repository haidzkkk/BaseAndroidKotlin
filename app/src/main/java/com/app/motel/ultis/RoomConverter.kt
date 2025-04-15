package com.app.motel.ultis

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}

