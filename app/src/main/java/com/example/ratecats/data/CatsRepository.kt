package com.example.ratecats.data

import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.catsapi.CatsApi
import com.example.ratecats.data.catsapi.FavouriteImage
import com.example.ratecats.data.room.CatsRoomDatabase
import com.example.ratecats.data.room.LocalFavoritedImg
import kotlinx.coroutines.flow.Flow

private const val TAG = "Repo__TAG"
private const val SUB_ID = "00005"

class CatsRepository(private val roomDb: CatsRoomDatabase) {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getFavoritesFromAPI(): List<FavouriteImage> {
        val favourites = CatsApi.catsApiService.getMyFavorites(SUB_ID)
        return if (favourites.isSuccessful) favourites.body()!! else listOf()
    }
    fun getSavedPhotos(): Flow<List<LocalFavoritedImg>> =
        roomDb.favoritedImgDao().getFavoritedImages()
    suspend fun addFavorite(imgId: String) =
        CatsApi.catsApiService.addFavorite(FavouriteImage(imgId, SUB_ID))
    suspend fun removeFavorite(favoriteId: String) =
        CatsApi.catsApiService.removeFavorite(favoriteId)
}