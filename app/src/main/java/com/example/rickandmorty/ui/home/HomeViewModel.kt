package com.example.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.CharacterRepository
import com.example.rickandmorty.data.models.Characters

class HomeViewModel : ViewModel() {
     private val repository = CharacterRepository()
    suspend fun fecthing(): Characters? = repository.getAllCh()
}