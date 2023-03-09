package com.example.rickandmorty.domain

import com.example.rickandmorty.data.CharacterRepository
import com.example.rickandmorty.data.models.Characters

class GetDeadCharacterUseCase {
    private val repository = CharacterRepository()
    suspend operator fun invoke(page: Int): Characters? {
        return repository.getDeadCh(page)
    }
}