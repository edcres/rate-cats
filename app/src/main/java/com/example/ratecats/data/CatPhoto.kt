package com.example.ratecats.data

import com.squareup.moshi.Json

data class CatPhoto (
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val imgSrcUrl: String,
    // todo: the breed attribute might not work
    @Json(name = "breeds")
    val breeds: List<Any>,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
)