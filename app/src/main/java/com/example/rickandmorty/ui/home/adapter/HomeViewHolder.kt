package com.example.rickandmorty.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.ItemCharacterListBinding

class HomeViewHolder(private val itemView: View, listener: HomeAdapter.onItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bindign = ItemCharacterListBinding.bind(itemView)
    fun bind(item: ResultsModel) {
        bindign.textViewNameCharacter.text = item.name
        Glide.with(itemView.context).load(item.image).into(bindign.textViewUrl)
    }

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}
