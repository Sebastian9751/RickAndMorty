package com.example.rickandmorty.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters(
    val results: List<ResultsModel>
) : Parcelable
