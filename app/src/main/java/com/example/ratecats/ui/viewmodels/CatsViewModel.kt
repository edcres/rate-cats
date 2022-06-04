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

    init {
        getAllPhotos()
        getAllGifs()
    }

    // DATABASE QUERIES //
    fun getAllPhotos() = viewModelScope.launch { _allPhotos.postValue(repo.getAllPhotos()) }
    fun getAllGifs() = viewModelScope.launch { _allGifs.postValue(repo.getAllGifs()) }
    // DATABASE QUERIES //
}