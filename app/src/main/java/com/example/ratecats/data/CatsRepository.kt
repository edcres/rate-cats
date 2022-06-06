package com.example.ratecats.data

class CatsRepository {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getMyFavorites(): List<CatPhoto> = CatsApi.catsApiService.getMyFavorites()
    suspend fun addFavorite(favoriteId: String) = CatsApi.catsApiService.addFavorite(favoriteId)
    suspend fun removeFavorite(favoriteId: String) = CatsApi.catsApiService.removeFavorite(favoriteId)
}