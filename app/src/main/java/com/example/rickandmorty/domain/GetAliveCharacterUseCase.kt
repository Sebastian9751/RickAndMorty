package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharacterRepository
import com.example.rickandmorty.data.models.Characters

class GetAliveCharacterUseCase {
    private val repository = CharacterRepository()
    suspend operator fun invoke(): Characters? {
        return repository.getAliveCh()
    }
}