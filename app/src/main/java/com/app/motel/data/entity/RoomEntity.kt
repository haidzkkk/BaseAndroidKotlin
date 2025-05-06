package com.app.motel.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.motel.data.model.Rooms
import com.app.motel.ultis.StringListConverter

@Entity(tableName = "room")
data class RoomEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val country: String?,
    val location: String?,
    val follow: Int?,
    @TypeConverters(StringListConverter::class)
    val image: List<String>?,
){
    fun toModel(): Rooms {
        return Rooms(
            id = id,
            name = name,
            country = country,
            location = location,
            image = image,
            follow = follow
        )
    }
}