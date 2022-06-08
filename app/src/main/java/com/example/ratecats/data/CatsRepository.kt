package com.example.ratecats.data

import android.util.Log
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.catsapi.CatsApi
import com.example.ratecats.data.catsapi.FavouriteImage

private const val TAG = "Repo__TAG"
private const val SUB_ID = "00005"

class CatsRepository {
    suspend fun getAllPhotos(): List<CatPhoto> = CatsApi.catsApiService.getAllPhotos()
    suspend fun getAllGifs(): List<CatPhoto> = CatsApi.catsApiService.getAllGifs()
    suspend fun getMyFavorites(): List<CatPhoto> {
//        Log.d(TAG, "getMyFavorites: called")

        Log.d(TAG, "getMyFavorites: this 1 is called")
        val favourites = CatsApi.catsApiService.getMyFavorites(SUB_ID)
//        Log.d(TAG, "getMyFavorites: $favourites")


        Log.d(TAG, "getMyFavorites: this 2 is called")
        return if (favourites.isSuccessful) {

            val images = favourites.body()!!
//            Log.d(TAG, "favourites: success ${images.size}\n $images")
            Log.d(TAG, "images: $images")
            images
            listOf()
        } else {
//            Log.d(TAG, "getMyFavorites: error body ${favourites.errorBody().toString()}")
//            Log.d(TAG, "getMyFavorites: ${favourites.message()}")
            listOf()
        }


//        val asd = favourites.enqueue(
//            object : Callback<List<CatPhoto>> {
//                override fun onResponse(call: Call<List<CatPhoto>>, response: Response<List<CatPhoto>>) {
//                    val images = response.body() ?: return
//                    Log.d(TAG, "images: ${images.size}\n$images")
//                }
//
//                override fun onFailure(call: Call<List<CatPhoto>>, t: Throwable) =
//                    t.printStackTrace()
//            }
//        )
    }
    suspend fun addFavorite(imgId: String, imgUrl: String) {
        CatsApi.catsApiService.addFavorite(FavouriteImage(imgId, SUB_ID))
//        CatsApi.catsApiService.addFavorite(FavouriteImage(imgId, imgUrl,SUB_ID))
    }
    suspend fun removeFavorite(favoriteId: String) = CatsApi.catsApiService.removeFavorite(favoriteId)
}