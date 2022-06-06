package com.example.ratecats.data

import com.example.ratecats.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.thecatapi.com"
private const val API_V = "v1"
private const val API_KEY_VAR = "x-api-key"
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
    suspend fun getAllPhotos(): List<CatPhoto>
    @GET("$API_V/images/search?mime_types=gif&limit=100")
    suspend fun getAllGifs(): List<CatPhoto>

    // Favorites
    @Headers("$API_KEY_VAR: ${BuildConfig.CATS_API_KEY}")
    @GET("$API_V/favourites?limit=100")
    suspend fun getMyFavorites(
        @Query("sub_id") subId: String = SUB_ID
    ): List<CatPhoto>
    @Headers("$API_KEY_VAR: ${BuildConfig.CATS_API_KEY}")
//    @POST("$API_V/favourites?sub_id=$SUB_ID")
//    suspend fun addFavorite(
//        @Query("image_id") imgID: String
//    )
    @POST("$API_V/favourites?limit=100")
    suspend fun addFavorite(
       @Body image_id: String,
       @Body sub_id: String = SUB_ID
    )
    @DELETE("$API_V/favourites")
    suspend fun removeFavorite(
        @Query ("favourite_id")image_id: String,
    )
}

object CatsApi {
    val catsApiService : CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}