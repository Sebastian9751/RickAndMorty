package com.example.rickandmorty.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel

class HomeAdapter (private val characterList: List<ResultsModel>, private  val  onClickListener :(ResultsModel) ->Unit) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  HomeViewHolder(layoutInflater.inflate(R.layout.item_character_list, parent, false))
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = characterList[position]
        holder.render(item, onClickListener)
}
}

