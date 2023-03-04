package com.example.rickandmorty.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.ItemCharacterListBinding

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterListBinding.bind(view)
    fun render(character:ResultsModel, onClickListener :(ResultsModel) ->Unit) {
        binding.textViewNameCharacter.text = character.name

        Glide.with(itemView.context).load(character.image).into(binding.imgViewUrl)

        itemView.setOnClickListener {
            onClickListener(character)
        }
    }
}
