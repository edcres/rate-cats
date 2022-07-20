package com.example.ratecats.data.catsapi

data class FavImgResponse(
    val id: String,
    val image_id: String,
    val sub_id: String,
    val image: Image
) {
    // Couldn't store this data class in Room bs it doesn't accept custom objects.
    // Maybe use a type converter.
    data class Image(
        val id: String,
        val url: String
    )
}