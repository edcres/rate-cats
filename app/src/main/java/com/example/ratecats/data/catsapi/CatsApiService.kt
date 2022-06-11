package com.example.ratecats.data.catsapi

import com.example.ratecats.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.thecatapi.com"
private const val API_V = "v1"
private const val API_KEY_VAR = "x-api-key"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
// http interceptor for logging
private val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    )
    .build()

interface CatsApiService {
    @GET("$API_V/images/search?mime_types=jpg,png&limit=100")
    suspend fun getAllPhotos(): List<CatPhoto>

    @GET("$API_V/images/search?mime_types=gif&limit=100")
    suspend fun getAllGifs(): List<CatPhoto>

    // Favorites
    @Headers("$API_KEY_VAR: ${BuildConfig.CATS_API_KEY}")
    @GET("$API_V/favourites?limit=100")
    suspend fun getMyFavorites(
        @Query("sub_id") subId: String
    ): Response<List<FavImgResponse>>

    @Headers("$API_KEY_VAR: ${BuildConfig.CATS_API_KEY}")
    @POST("$API_V/favourites?limit=100")
    suspend fun addFavorite(
        @Body favImageSend: FavImageSend
    ): Response<Any>

    @Headers("$API_KEY_VAR: ${BuildConfig.CATS_API_KEY}")
    @DELETE("$API_V/favourites/{id}")
    suspend fun removeFavorite(
        @Path("id") id: String,
    ): Response<String>
}

object CatsApi {
    val catsApiService : CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}