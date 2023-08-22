package com.example.travle.data.model

data class Travle(
    val id: String,
    val name: String,
    val country: String,
    val location: String,
    val follow: Int,
    val image: String,
    val comment: List<String>,
    ) {
}