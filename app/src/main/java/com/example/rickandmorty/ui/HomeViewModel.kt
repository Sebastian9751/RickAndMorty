package com.example.rickandmorty.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Chprovide
import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.domain.GetChUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val characterModel = MutableLiveData<Characters?>()
    val getChUseCase = GetChUseCase()

    fun onCreate() {
        viewModelScope.launch {
            val result = getChUseCase()

            if (result != null) {
                characterModel.postValue(result)
                Log.i("hey", "${result.results.size}")
            } else {
                Log.i("hey", "No se encontraron personajes")
            }
        }
    }

    fun ramDom() {

    }
}
