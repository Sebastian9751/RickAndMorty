package com.example.rickandmorty.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rtf {
    fun  getRtf():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}