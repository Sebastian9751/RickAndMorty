package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.models.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiClient {
    @GET("character/")
    suspend fun getAllCh(@Query("page") page: Int): Response<Characters>

    @GET("character/?status=alive")
    suspend fun getAliveCh(@Query("page") page: Int): Response<Characters>

    @GET("character/?status=dead")
    suspend fun getDeadCh(@Query("page") page: Int): Response<Characters>
}