package com.example.ratecats.data

import android.util.Log
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.catsapi.CatsApi
import com.example.ratecats.data.catsapi.FavouriteImage
import com.example.ratecats.data.room.LocalFavoritedImg

private const val TAG = "Repo__TAG"
private const val SUB_ID = "00005"

class CatsRepository {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getFavoritesFromAPI(): List<FavouriteImage> {
        val favourites = CatsApi.catsApiService.getMyFavorites(SUB_ID)
        return if (favourites.isSuccessful) favourites.body()!! else listOf()
    }
    suspend fun getSavedPhotos(): List<LocalFavoritedImg> = ;
    suspend fun addFavorite(imgId: String) {
        CatsApi.catsApiService.addFavorite(FavouriteImage(imgId, SUB_ID))
    }
    suspend fun removeFavorite(favoriteId: String) = CatsApi.catsApiService.removeFavorite(favoriteId)
}