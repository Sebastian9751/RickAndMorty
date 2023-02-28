package com.example.rickandmorty.data

import com.example.rickandmorty.CharacterProvide
import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.network.CharacterService

class CharacterRepository {

    private val api = CharacterService()
    suspend fun getAllCh(): Characters? {
        val response = api.getCh()
        CharacterProvide.ch = response
        return response
    }
}