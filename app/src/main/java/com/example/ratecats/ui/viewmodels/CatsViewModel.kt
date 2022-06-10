package com.example.ratecats.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.CatsRepository
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
        getSavedFavourites()
        getAllPhotos()
        getAllGifs()
        getFavoritesFromAPI()
    }
    // SETUP //

    // DATABASE QUERIES //
    private fun getAllPhotos() = viewModelScope.launch {
        try {
            _allPhotos.postValue(repo.getAllPhotos())
        } catch (error: Exception) {
            Log.e(TAG, "getAllPhotos: $error")
            // Expected an int but was NULL at path $[99].width
        }
    }
    private fun getAllGifs() = viewModelScope.launch { _allGifs.postValue(repo.getAllGifs()) }
    private fun getFavoritesFromAPI() = viewModelScope.launch {
        // Can use this to check if the Web API and Room match
        Log.i(TAG, "getFavoritesFromAPI: favourites size = ${repo.getFavoritesFromAPI().size}")
        Log.i(TAG, "getFavoritesFromAPI: favourites = ${repo.getFavoritesFromAPI()}")
    }
    private fun getSavedFavourites() = viewModelScope.launch {
        repo.getSavedPhotos().collect { _savedFavourites.postValue(it) }
    }
    fun addFavorite(img: LocalFavoritedImg) = viewModelScope.launch {
        if(repo.addFavorite(img.imgId).isSuccessful){
            repo.insert(img)
        }
    }
    fun removeFavorite(img: LocalFavoritedImg) = viewModelScope.launch {
        Log.d(TAG, "removeFavorite: id to delete = ${img.imgId}")
        if (repo.removeFavorite(img.imgId).isSuccessful) {
            repo.delete(img)
            Log.d(TAG, "removeFavorite: deleted")
        } else {
            Log.d(TAG, "removeFavorite: delete failed")
        }
    }
    // DATABASE QUERIES //
}