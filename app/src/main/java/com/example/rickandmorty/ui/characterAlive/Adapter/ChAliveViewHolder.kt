package com.example.rickandmorty.ui.characterAlive.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.ItemChAliveListBinding

class ChAliveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemChAliveListBinding.bind(view)
    fun render(chAlive: ResultsModel, onClickListener: (ResultsModel)->Unit){
        binding.textViewNameCharacter.text = chAlive.name
        val icon = chAlive.species
        setIcon(icon)
        Glide.with(itemView.context).load(chAlive.image).into(binding.imgViewUrl)
        itemView.setOnClickListener {
            onClickListener(chAlive)
        }
    }
    private fun setIcon(icon: String) {
        val ic = when(icon) {
            itemView.context.getString(R.string.alien_text) -> itemView.context.getString(R.string.alien_icon)
            itemView.context.getString(R.string.human_text) -> itemView.context.getString(R.string.human_icon)
            else -> itemView.context.getString(R.string.unknown_icon)
        }
        binding.IconText.text = ic
    }
}
