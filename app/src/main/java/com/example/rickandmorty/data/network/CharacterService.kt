package com.example.rickandmorty.data.network

import com.example.rickandmorty.core.RetrofitHelper
import com.example.rickandmorty.data.models.Characters


class CharacterService {
    private val rtf = RetrofitHelper.getRetrofit()
    suspend fun getCh(page: Int): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAllCh(page)
        return res.body()
    }

    suspend fun getAliveCh(): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAliveCh()
        return res.body()
    }

    suspend fun getDeadCh(): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getDeadCh()
        return res.body()
    }
}