package com.example.rickandmorty.ui.characterDead

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.FragmentCharacterDeadBinding
import com.example.rickandmorty.domain.GetDeadCharacterUseCase
import com.example.rickandmorty.ui.characterDead.adapter.ChDeadAdapter
import kotlinx.coroutines.launch


class CharacterDeadFragment : Fragment() {

    private var _binding: FragmentCharacterDeadBinding? = null
    private val binding get() = _binding!!
    private val result = GetDeadCharacterUseCase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipe.isEnabled = false
        binding.swipe.isRefreshing = true
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        setRecyclerView()
    }

    private fun setRecyclerView() {
        lifecycleScope.launch {
            val response = result.invoke()
            response?.results?.let { results ->
                val adapter = ChDeadAdapter(results) { ch -> onItemSelect(ch) }
                binding.recyclerView.adapter = adapter
            }
            binding.swipe.isRefreshing = false
            Log.i("hellooDead", "$response")
        }
    }


    private fun onItemSelect(characterss: ResultsModel) {
        val direction =
            CharacterDeadFragmentDirections.actionCharacterDeadFragmentToCharacterDetailFragment(
                characterss
            )
        findNavController().navigate(direction)
    }


}