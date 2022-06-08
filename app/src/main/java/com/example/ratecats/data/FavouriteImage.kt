package com.example.ratecats.data

import com.squareup.moshi.Json

data class FavouriteImage (
    val image_id: String,
    @Json(name = "url")
    val imgSrcUrl: String,
    val sub_id: String,
)