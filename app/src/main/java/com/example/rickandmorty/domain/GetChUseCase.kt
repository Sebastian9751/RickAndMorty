package com.example.rickandmorty.domain

import com.example.rickandmorty.data.ChRepository
import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.models.ResultsModel

class GetChUseCase {


    private val repository = ChRepository()

    suspend operator fun invoke(): Characters? {
        return repository.getAllCh()
    }
}