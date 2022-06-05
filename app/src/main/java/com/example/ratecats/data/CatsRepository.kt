package com.example.ratecats.data

class CatsRepository {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getMyFavorites(): List<CatPhoto> = CatsApi.catsApiService.getMyFavorites()
}