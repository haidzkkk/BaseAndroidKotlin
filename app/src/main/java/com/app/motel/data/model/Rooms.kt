package com.history.vietnam.data.model

import com.history.vietnam.data.entity.RoomEntity

data class Rooms(
    val id: String,
    val name: String?,
    val country: String?,
    val location: String?,
    val follow: Int?,
    val image: List<String>?,
    ) {

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