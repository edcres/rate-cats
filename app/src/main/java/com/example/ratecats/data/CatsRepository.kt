package com.example.ratecats.data

import androidx.annotation.WorkerThread
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.catsapi.CatsApi
import com.example.ratecats.data.catsapi.FavImgResponse
import com.example.ratecats.data.catsapi.FavImageSend
import com.example.ratecats.data.room.CatsRoomDatabase
import com.example.ratecats.data.room.LocalFavoritedImg
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

private const val TAG = "Repo__TAG"
private const val SUB_ID = "00001"

class CatsRepository {

    lateinit var roomDb: CatsRoomDatabase

    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getFavoritesFromAPI(): List<FavImgResponse> {
        val favourites = CatsApi.catsApiService.getMyFavorites(SUB_ID)
        return if (favourites.isSuccessful) favourites.body()!! else listOf()
    }

    suspend fun addFavorite(imgId: String): Response<Any> {
        return CatsApi.catsApiService.addFavorite(FavImageSend(imgId, SUB_ID))
    }

    suspend fun removeFavorite(favoriteId: String) =
        CatsApi.catsApiService.removeFavorite(favoriteId)

    // Local
    @WorkerThread
    fun getLocalFavs(): Flow<List<LocalFavoritedImg>> =
        roomDb.favoritedImgDao().getFavoritedImages()

    @WorkerThread
    suspend fun insertLocal(localFavoritedImg: LocalFavoritedImg) =
        roomDb.favoritedImgDao().insert(localFavoritedImg)

    @WorkerThread
    suspend fun deleteLocal(localFavoritedImg: LocalFavoritedImg) =
        roomDb.favoritedImgDao().delete(localFavoritedImg)
}