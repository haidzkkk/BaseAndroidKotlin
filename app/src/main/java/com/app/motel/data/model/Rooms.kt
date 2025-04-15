package com.app.motel.data.model

import com.app.motel.data.entity.RoomEntity

data class Rooms(
    val id: String,
    val name: String?,
    val country: String?,
    val location: String?,
    val follow: Int?,
    val image: List<String>?,
    ) {

    companion object{
        fun fromEntity(entity: RoomEntity): Rooms {
            return Rooms(
                id = entity.id,
                name = entity.name,
                country = entity.country,
                location = entity.location,
                image = entity.image,
                follow = entity.follow
            )
        }
    }

    fun toEntity(): RoomEntity {
        return RoomEntity(
            id = id,
            name = name,
            country = country,
            location = location,
            image = image,
            follow = follow
        )
    }
}