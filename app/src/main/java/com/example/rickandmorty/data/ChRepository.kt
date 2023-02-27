package com.example.rickandmorty.data

import com.example.rickandmorty.Chprovide
import com.example.rickandmorty.data.models.Characters
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.data.network.CharacterService

class ChRepository {

    private val api =  CharacterService()
    suspend fun getAllCh(): Characters? {
        val response = api.getCh()
        Chprovide.ch = response
        return response
    }
}