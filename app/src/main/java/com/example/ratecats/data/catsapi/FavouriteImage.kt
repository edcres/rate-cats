package com.example.ratecats.data.catsapi

data class FavouriteImage(
    val id: String,
    val image_id: String,
    val sub_id: String,
    val image: Image
) {
    data class Image(
        val id: String,
        val url: String
    )
}