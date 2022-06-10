package com.example.ratecats.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.CatsRepository
import com.example.ratecats.data.catsapi.FavImgResponse
import com.example.ratecats.data.room.CatsRoomDatabase
import com.example.ratecats.data.room.LocalFavoritedImg
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "SharedVM__TAG"

class CatsViewModel: ViewModel() {

    private lateinit var roomDb: CatsRoomDatabase
    private lateinit var repo: CatsRepository
    private val _allPhotos = MutableLiveData<List<CatPhoto>>()
    val photos: LiveData<List<CatPhoto>> = _allPhotos
    private val _allGifs = MutableLiveData<List<CatPhoto>>()
    val allGifs: LiveData<List<CatPhoto>> = _allGifs

    // todo: observe these
    private val _apiFavourites = MutableLiveData<List<FavImgResponse>>()
    val apiFavourites: LiveData<List<FavImgResponse>> = _apiFavourites

    private val _savedFavourites = MutableLiveData<List<LocalFavoritedImg>>()
    val savedFavourites: LiveData<List<LocalFavoritedImg>> = _savedFavourites

    // HELPERS //
    fun favoritesContainsId(imgId: String): Boolean {
        // todo: there's probably a more efficient way to do this, now the saved favorites list is cycled for every reccycler item in Categories and Gifs
        //      - probably do something with a map

        _savedFavourites.value!!.forEach {
            if(it.imgId == imgId) return true
        }
        return false
    }
    // HELPERS //

    // SETUP //
    fun setupLocalBackend(application: Application) {
        roomDb = CatsRoomDatabase.getInstance(application)
        repo = CatsRepository(roomDb)
        getFavoritesFromAPI()
        getAllPhotos()
        getAllGifs()
        getFavoritesFromAPI()
    }
    // SETUP //

    // DATABASE QUERIES //
    private fun getAllPhotos() = viewModelScope.launch {
        try {
            _allPhotos.postValue(repo.getAllPhotos())
        } catch (exception: Exception) {
            Log.e(TAG, "getAllPhotos: $exception")
            // Expected an int but was NULL at path $[99].width
        }
    }
    private fun getAllGifs() = viewModelScope.launch {
        try {
            _allGifs.postValue(repo.getAllGifs())
        } catch (exception: Exception) {
            // Probably bad internet connection
            Log.e(TAG, "getAllGifs: $exception")
        }

    }
    private fun getFavoritesFromAPI() = viewModelScope.launch {
        // Can use this to check if the Web API and Room match
        try {
            _apiFavourites.postValue(repo.getFavoritesFromAPI())
        } catch (exception: java.lang.Exception) {
            getSavedFavourites()
        }
    }
    private fun getSavedFavourites() = viewModelScope.launch {
        repo.getLocalFavs().collect { _savedFavourites.postValue(it) }
    }
    fun addFavorite(img: LocalFavoritedImg) = viewModelScope.launch {
        if(repo.addFavorite(img.imgId).isSuccessful){
            repo.insertLocal(img)
        }
    }
    fun removeFavorite(img: LocalFavoritedImg) = viewModelScope.launch {
        Log.d(TAG, "removeFavorite: id to delete = ${img.imgId}")
        if (repo.removeFavorite(img.imgId).isSuccessful) {
            repo.deleteLocal(img)
            Log.d(TAG, "removeFavorite: deleted")
        } else {
            Log.d(TAG, "removeFavorite: delete failed")
        }
    }
    // DATABASE QUERIES //
}