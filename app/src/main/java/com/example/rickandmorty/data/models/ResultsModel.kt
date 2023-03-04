package com.example.rickandmorty.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultsModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image : String
) : Parcelable
