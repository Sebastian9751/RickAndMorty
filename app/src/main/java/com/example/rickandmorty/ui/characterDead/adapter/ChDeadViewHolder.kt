package com.example.rickandmorty.ui.characterDead.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.ItemCharacterDeadListBinding

class ChDeadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCharacterDeadListBinding.bind(view)

    fun render(character: ResultsModel, onClickListener: (ResultsModel) -> Unit) {
        val icon = character.species
        binding.textViewNameCharacter.text = character.name
        setIcon(icon)
        Glide.with(itemView.context).load(character.image).into(binding.imgViewUrl)
        itemView.setOnClickListener {
            onClickListener(character)
        }
    }

    private fun setIcon(icon: String) {
        val ic = when (icon) {
            itemView.context.getString(R.string.alien_text) -> itemView.context.getString(R.string.alien_icon)
            itemView.context.getString(R.string.human_text) -> itemView.context.getString(R.string.human_icon)
            else -> itemView.context.getString(R.string.unknown_icon)
        }
        binding.IconText.text = ic
    }
}