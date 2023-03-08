package com.example.rickandmorty.ui.characterAlive.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.models.ResultsModel

class ChAliveAdapter (
    private val chAliveList: List<ResultsModel>,
    private val onClickListener: (ResultsModel) -> Unit
) : RecyclerView.Adapter<ChAliveViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChAliveViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = chAliveList.size

    override fun onBindViewHolder(holder: ChAliveViewHolder, position: Int) {
        val item = chAliveList[position]
        holder.render(item,onClickListener)
    }

}