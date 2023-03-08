package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.models.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiClient {
    @GET("character/")
    suspend fun getAllCh(@Query("page") page: Int? = 1): Response<Characters>

    @GET("character/?status=alive")
    suspend fun getAliveCh(): Response<Characters>
}