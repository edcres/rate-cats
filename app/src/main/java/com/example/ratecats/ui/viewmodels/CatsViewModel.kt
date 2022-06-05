package com.example.ratecats.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratecats.data.CatPhoto
import com.example.ratecats.data.CatsRepository
import kotlinx.coroutines.launch

class CatsViewModel: ViewModel() {

    private val repo = CatsRepository()
    private val _allPhotos = MutableLiveData<List<CatPhoto>>()
    val photos: LiveData<List<CatPhoto>> = _allPhotos
    private val _allGifs = MutableLiveData<List<CatPhoto>>()
    val allGifs: LiveData<List<CatPhoto>> = _allGifs
    private val _allMyFavorites = MutableLiveData<List<CatPhoto>>()
    val allMyFavorites: LiveData<List<CatPhoto>> = _allMyFavorites

    init {
        getAllPhotos()
        getAllGifs()
        getAllMyFavorites()
    }

    // DATABASE QUERIES //
    fun getAllPhotos() = viewModelScope.launch { _allPhotos.postValue(repo.getAllPhotos()) }
    fun getAllGifs() = viewModelScope.launch { _allGifs.postValue(repo.getAllGifs()) }
    fun getAllMyFavorites() =
        viewModelScope.launch { _allMyFavorites.postValue(repo.getMyFavorites()) }
    fun addFavorite(favoriteId: String) = viewModelScope.launch { repo.addFavorite(favoriteId) }
    // DATABASE QUERIES //
}