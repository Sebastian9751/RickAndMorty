package com.example.rickandmorty.ui.characterAlive

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.FragmentCharacterAliveBinding
import com.example.rickandmorty.domain.GetAliveCharacterUseCase
import com.example.rickandmorty.ui.characterAlive.adapt.ChAliveAdapter
import kotlinx.coroutines.launch


class CharacterAliveFragment : Fragment() {

    private var _binding: FragmentCharacterAliveBinding? = null
    private val binding get() = _binding!!
    private val result = GetAliveCharacterUseCase()
    private val characterList = mutableListOf<ResultsModel>()
    private lateinit var viewModel: CharacterAliveViewModel
    private var PAGUE = 1
    private lateinit var adapter: ChAliveAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterAliveBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterAliveViewModel::class.java]
        viewModel = CharacterAliveViewModel()
        binding.swipe.isEnabled = false
        //binding.swipe.isRefreshing = true
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        adapter = ChAliveAdapter(characterList) { ch -> onItemSelect(ch) }
        binding.recyclerView.adapter = adapter

        setRecyclerView()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    PAGUE += 1
                    setRecyclerView()
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecyclerView() {
        lifecycleScope.launch {
            binding.swipe.isRefreshing = true
            // Guardar la posicion actual del reciclador en el viewModel
            viewModel.currentVisiblePosition = (
                    binding.recyclerView.layoutManager as LinearLayoutManager
                    ).findFirstCompletelyVisibleItemPosition()

            val response = result.invoke(PAGUE)
            response?.results?.let { results ->
                characterList.addAll(results)
                adapter.notifyDataSetChanged()
            }
            binding.swipe.isRefreshing = false
            Log.i("hellAlive", "$response")
        }
    }


    private fun onItemSelect(characterModel: ResultsModel) {
        val direction =
            CharacterAliveFragmentDirections.actionCharacterAliveFragmentToCharacterDetailFragment(
                characterModel
            )
        findNavController().navigate(direction)
    }

}