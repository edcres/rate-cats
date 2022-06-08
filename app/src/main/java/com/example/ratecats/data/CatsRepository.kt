package com.example.ratecats.data

import android.util.Log

private const val TAG = "Repo__TAG"
private const val SUB_ID = "00005"

class CatsRepository {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getMyFavorites(): List<CatPhoto> {
        Log.d(TAG, "getMyFavorites: called")
        val favourites = CatsApi.catsApiService.getMyFavorites(SUB_ID)
        Log.d(TAG, "getMyFavorites: $favourites")
        return favourites
    }
    suspend fun addFavorite(imgId: String, imgUrl: String) {
        CatsApi.catsApiService.addFavorite(
            FavouriteImage(imgId, imgUrl,SUB_ID)
        )
    }
    suspend fun removeFavorite(favoriteId: String) = CatsApi.catsApiService.removeFavorite(favoriteId)
}