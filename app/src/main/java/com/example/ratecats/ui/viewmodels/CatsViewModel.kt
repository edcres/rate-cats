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

    init {
        getAllPhotos()
        getAllGifs()
        getFavoritesFromAPI()
    }

    // SETUP //
    fun setupLocalBackend(application: Application) {
        // todo: call this
        roomDb = CatsRoomDatabase.getInstance(application)
        repo = CatsRepository(roomDb)
        getSavedFavourites()
    }
    // SETUP //

    // DATABASE QUERIES //
    private fun getAllPhotos() = viewModelScope.launch { _allPhotos.postValue(repo.getAllPhotos()) }
    private fun getAllGifs() = viewModelScope.launch { _allGifs.postValue(repo.getAllGifs()) }
    private fun getFavoritesFromAPI() = viewModelScope.launch {
        // Can use this to check if the Web API and Room match
        Log.i(TAG, "getFavoritesFromAPI: favourites size = ${repo.getFavoritesFromAPI().size}")
    }
    private fun getSavedFavourites() = viewModelScope.launch {
        repo.getSavedPhotos().collect { _savedFavourites.postValue(it) }
    }
    fun addFavorite(img: LocalFavoritedImg) = viewModelScope.launch {
        repo.addFavorite(img.imgId)
        // todo: add it to Room
        // only if it's successful in the API
        repo.insert(img)
    }
    fun removeFavorite(favoriteId: String) = viewModelScope.launch {
        repo.removeFavorite(favoriteId)
        // todo: remove it from Room
        // only if it's successful in the API
    }
    // DATABASE QUERIES //
}