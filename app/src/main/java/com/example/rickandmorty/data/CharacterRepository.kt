package com.example.rickandmorty.data

import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.network.CharacterService

class CharacterRepository {

    private val api = CharacterService()
    suspend fun getAllCh(page: Int): Characters? = api.getCh(page)

    suspend fun getAliveCh(): Characters? = api.getAliveCh()
    suspend fun getDeadCh(): Characters? = api.getDeadCh()


}