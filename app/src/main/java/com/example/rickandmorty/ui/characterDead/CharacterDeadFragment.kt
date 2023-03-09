package com.example.rickandmorty.ui.characterDead

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.FragmentCharacterDeadBinding
import com.example.rickandmorty.domain.GetDeadCharacterUseCase
import com.example.rickandmorty.ui.characterDead.adapter.ChDeadAdapter
import kotlinx.coroutines.launch


class CharacterDeadFragment : Fragment() {

    private var _binding: FragmentCharacterDeadBinding? = null
    private val binding get() = _binding!!
    private val result = GetDeadCharacterUseCase()
    private val characterList = mutableListOf<ResultsModel>()
    private lateinit var viewModel: CharacterDeadViewModel
    private var PAGUE = 1
    private lateinit var adapter: ChDeadAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterDeadViewModel::class.java]
        viewModel = CharacterDeadViewModel()
        binding.swipe.isEnabled = false
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        adapter = ChDeadAdapter(characterList) { ch -> onItemSelect(ch) }
        binding.recyclerView.adapter = adapter

        setRecyclerView()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if(lastVisibleItemPosition == totalItemCount - 1) {
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
            viewModel.currentVisiblePosition = (
                    binding.recyclerView.layoutManager as LinearLayoutManager
                    ).findFirstCompletelyVisibleItemPosition()
            val response = result.invoke(PAGUE)
            response?.results?.let { results ->
                characterList.addAll(results)
                adapter.notifyDataSetChanged()
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