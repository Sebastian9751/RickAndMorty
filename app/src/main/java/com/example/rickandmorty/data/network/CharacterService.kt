package com.example.rickandmorty.data.network

import com.example.rickandmorty.core.RetrofitHelper
import com.example.rickandmorty.data.models.Characters


class CharacterService {
    private val rtf = RetrofitHelper.getRetrofit()
    suspend fun getCh(): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAllCh()
        return res.body()
    }
}