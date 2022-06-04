package com.example.ratecats.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ratecats.data.CatsRepository

class CatsViewModel: ViewModel() {
    private val repo = CatsRepository()

}