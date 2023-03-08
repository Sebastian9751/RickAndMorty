package com.example.rickandmorty.ui.characterAlive

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.FragmentCharacterAliveBinding
import com.example.rickandmorty.domain.GetAliveCharacterUseCase
import com.example.rickandmorty.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.launch


class CharacterAliveFragment : Fragment() {

    private var _binding: FragmentCharacterAliveBinding? = null
    private val binding get() = _binding!!
    private val chAliveViewModel: ChAliveViewModel by viewModels()
    private val result = GetAliveCharacterUseCase()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterAliveBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        chAliveViewModel.viewModelScope.launch {
            val response = result.invoke()
            response?.results?.let { results ->
                val adapter = HomeAdapter(results) { ch -> onItemSelect(ch) }
                val manager = LinearLayoutManager(requireContext())
                val decoration = DividerItemDecoration(requireContext(), manager.orientation)
                binding.recyclerView.layoutManager = manager
                binding.recyclerView.adapter = adapter
                binding.recyclerView.addItemDecoration(decoration)
            }
            Log.i("hellAlive", "$response")
        }
    }

    private fun onItemSelect(characterModel: ResultsModel) {
        val direction = CharacterAliveFragmentDirections.actionCharacterAliveFragmentToCharacterDetailFragment(characterModel)
        findNavController().navigate(direction)
    }

}