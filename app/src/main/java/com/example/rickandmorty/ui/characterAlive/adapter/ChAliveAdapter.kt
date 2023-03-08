package com.example.rickandmorty.ui.characterAlive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel

class ChAliveAdapter (
    private val chAliveList: List<ResultsModel>,
    private val onClickListener: (ResultsModel) -> Unit
) : RecyclerView.Adapter<ChAliveViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChAliveViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChAliveViewHolder(layoutInflater.inflate(R.layout.item_ch_alive_list, parent, false))
    }

    override fun getItemCount(): Int = chAliveList.size

    override fun onBindViewHolder(holder: ChAliveViewHolder, position: Int) {
        val item = chAliveList[position]
        holder.render(item,onClickListener)
    }

}