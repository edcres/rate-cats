package com.example.ratecats.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratecats.data.catsapi.CatPhoto
import com.example.ratecats.data.CatsRepository
import kotlinx.coroutines.launch

private const val TAG = "SharedVM__TAG"

class CatsViewModel: ViewModel() {

    private val repo = CatsRepository()
    private val _allPhotos = MutableLiveData<List<CatPhoto>>()
    val photos: LiveData<List<CatPhoto>> = _allPhotos
    private val _allGifs = MutableLiveData<List<CatPhoto>>()
    val allGifs: LiveData<List<CatPhoto>> = _allGifs
    private val _savedFavourites = MutableLiveData<List<CatPhoto>>()
    val savedFavourites: LiveData<List<CatPhoto>> = _savedFavourites

    init {
        getAllPhotos()
        getAllGifs()
        getSavedFavourites()
        getFavoritesFromAPI()
    }

    // DATABASE QUERIES //
    private fun getAllPhotos() = viewModelScope.launch { _allPhotos.postValue(repo.getAllPhotos()) }
    private fun getAllGifs() = viewModelScope.launch { _allGifs.postValue(repo.getAllGifs()) }
    private fun getFavoritesFromAPI() = viewModelScope.launch {
        // Can use this to check if the Web API and Room match
        Log.i(TAG, "getFavoritesFromAPI: favourites size = ${repo.getFavoritesFromAPI().size}")
    }
    private fun getSavedFavourites() = viewModelScope.launch { _savedFavourites.postValue(repo.) }
    fun addFavorite(imgId: String) = viewModelScope.launch {
        repo.addFavorite(imgId)
        // todo: add it to Room
    }
    fun removeFavorite(favoriteId: String) = viewModelScope.launch {
        repo.removeFavorite(favoriteId)
        // todo: remove it from Room
    }
    // DATABASE QUERIES //
}