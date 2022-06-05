package com.example.ratecats.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.thecatapi.com"
private const val API_V = "v1"
private const val SUB_ID = "00001"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatsApiService {
    @GET("$API_V/images/search?limit=100")
//    @GET("v1/images/search?mime_types=gif,jpg,png")
    suspend fun getAllPhotos(): List<CatPhoto>
    @GET("$API_V/images/search?mime_types=gif&limit=99")
    suspend fun getAllGifs(): List<CatPhoto>

    // favorites
    @GET("$API_V/favourites?sub_id=$SUB_ID&limit=100")
    suspend fun getMyFavorites(): List<CatPhoto>
}

object CatsApi {
    val catsApiService : CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}