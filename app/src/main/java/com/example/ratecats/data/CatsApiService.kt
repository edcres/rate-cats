package com.example.ratecats.data

import com.example.ratecats.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://api.thecatapi.com"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatsApiService {
    @GET("v1/images/search")
    suspend fun getAllPhotos(): List<CatPhoto>
    @GET("v1/images/search?mime_types=gif")
    suspend fun getAllGifs(): List<CatPhoto>
}

object CatsApi {
    val catsApiService : CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}