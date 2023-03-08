package com.example.rickandmorty.ui.characterAlive

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        binding.swipe.isEnabled = false
        binding.swipe.isRefreshing = true
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        setRecyclerView()
    }

    private fun setRecyclerView() {
        lifecycleScope.launch {
            val response = result.invoke()
            response?.results?.let { results ->
                val adapter = HomeAdapter(results) { ch -> onItemSelect(ch) }
                binding.recyclerView.adapter = adapter
            }
            binding.swipe.isRefreshing = false
            Log.i("hellAlive", "$response")
        }
    }


    private fun onItemSelect(characterModel: ResultsModel) {
        val direction = CharacterAliveFragmentDirections.actionCharacterAliveFragmentToCharacterDetailFragment(characterModel)
        findNavController().navigate(direction)
    }

}