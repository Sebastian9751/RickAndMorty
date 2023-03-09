package com.example.rickandmorty.data.network

import com.example.rickandmorty.core.RetrofitHelper
import com.example.rickandmorty.data.models.Characters


class CharacterService {
    private val rtf = RetrofitHelper.getRetrofit()
    suspend fun getCh(page:Int): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAllCh(page)
        return res.body()
    }

    suspend fun getAliveCh(page: Int): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getAliveCh(page)
        return res.body()
    }

    suspend fun getDeadCh(page: Int): Characters? {
        val res = rtf.create(CharacterApiClient::class.java).getDeadCh(page)
        return res.body()
    }
}