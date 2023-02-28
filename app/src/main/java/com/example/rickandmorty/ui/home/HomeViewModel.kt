package com.example.rickandmorty.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val getCharacterUseCase = GetCharacterUseCase()

    fun onCreate(adapter: HomeAdapter, view: View, context: Context) {
        val layoutManager = LinearLayoutManager(context)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        setRecyclerView(layoutManager, itemDecorator, recyclerView, adapter, context)
        setDetail(adapter, view)

    }

    val isLoading = MutableLiveData<Boolean>()

    private fun setDetail(adapter: HomeAdapter, view: View){
        adapter.setOnClickl(object  :HomeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val selectedObject = adapter.getItem(position)// obtener objeto seleccionado
                val name = selectedObject.id // obtener el nombre del objeto
                Toast.makeText(view.context, "hello $name", Toast.LENGTH_SHORT).show()
            }

        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerView(
        layoutManager: LinearLayoutManager,
        itemDecorator: DividerItemDecoration,
        recyclerView: RecyclerView,
        adapter: HomeAdapter,
        context: Context
    ) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(itemDecorator)

        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = getCharacterUseCase()

                if (result != null) {
                    adapter.setList(result.results.map {
                        ResultsModel(it.id, it.name, it.status, it.species, it.image)
                    })
                    adapter.notifyDataSetChanged()

                    isLoading.postValue(false)
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(context, "No se encontraron datos", Toast.LENGTH_SHORT).show()
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                // Mostrar mensaje de error
                Toast.makeText(
                    context, "Error_al cargar los datos: ${e.message}", Toast.LENGTH_SHORT
                ).show()
                isLoading.postValue(false)
            }
        }
    }


}
