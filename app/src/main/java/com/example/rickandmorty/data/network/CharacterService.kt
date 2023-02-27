package com.example.rickandmorty.data.network

import com.example.rickandmorty.core.Rtf
import com.example.rickandmorty.data.models.Characters


class CharacterService {
    private val rtf = Rtf.getRtf()

    suspend fun getCh(): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAllCh()
        return res.body()
    }
}