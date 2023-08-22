package com.example.travle.data.model

data class User(
    val id: String,
    val username: String,
    val password: String,
    val name: String,
    val avatar: Int,
    val follow: ArrayList<Travle>,
) {
}