package com.example.rickandmorty.ui.characterDead.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel


class ChDeadAdapter(
    private val chDeadList: List<ResultsModel>,
    private val onClickListener: (ResultsModel) -> Unit
) : RecyclerView.Adapter<ChDeadViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChDeadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChDeadViewHolder(
            layoutInflater.inflate(
                R.layout.item_character_dead_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = chDeadList.size

    override fun onBindViewHolder(holder: ChDeadViewHolder, position: Int) {
        val item = chDeadList[position]
        holder.render(item, onClickListener)
    }
}