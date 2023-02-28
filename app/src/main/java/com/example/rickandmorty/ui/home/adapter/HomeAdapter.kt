package com.example.rickandmorty.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private val characters = mutableListOf<ResultsModel>()
    private lateinit var mListener: onItemClickListener

    fun setList(characterList: List<ResultsModel>) {
        characters.clear()
        characters.addAll(characterList)
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickl(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val context = parent.context
        val infater = LayoutInflater.from(context)
        val view = infater.inflate(R.layout.item_character_list, parent, false)
        return HomeViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size
    fun getItem(position: Int): ResultsModel {
        return characters[position]
    }
}

