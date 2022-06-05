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
    // todo: possible bug: if the number I request is greater than the number available, only on is given to me
    //  - more than the list variable can handle, or bc I'm not using the API key
    //  - probably the API is limiting the query to 100, bc I cant even do 101
    @GET("v1/images/search?limit=100")
//    @GET("v1/images/search?mime_types=gif,jpg,png")
    suspend fun getAllPhotos(): List<CatPhoto>
    @GET("v1/images/search?mime_types=gif&limit=100")
    suspend fun getAllGifs(): List<CatPhoto>
}

object CatsApi {
    val catsApiService : CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }
}