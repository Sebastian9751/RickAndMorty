package com.example.rickandmorty.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.ItemCharacterListBinding

class HomeViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bindign = ItemCharacterListBinding.bind(itemView)
    fun bind(item: ResultsModel) {
        bindign.textViewNameCharacter.text = item.name

    }

}
