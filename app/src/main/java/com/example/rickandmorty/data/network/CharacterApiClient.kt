package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.models.Characters
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiClient {
    @GET("character/")
    suspend fun getAllCh(): Response<Characters>

    @GET("character/?status=alive")
    suspend fun getAliveCh(): Response<Characters>

    @GET("character/?status=dead")
    suspend fun getDeadCh(): Response<Characters>
}