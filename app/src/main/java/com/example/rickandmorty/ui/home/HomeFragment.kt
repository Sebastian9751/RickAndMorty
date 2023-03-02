package com.example.rickandmorty.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.ui.home.adapter.HomeAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList(view)
        setDetail(HomeAdapter(), view)
    }

    private fun setList(view: View) {
        homeViewModel.onCreate(HomeAdapter(), view, requireContext())
    }

    private fun setDetail(adapter: HomeAdapter, view: View){
        adapter.setOnClickl(object  :HomeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val selectedObject = adapter.getItem(position)// obtener objeto seleccionado
                homeViewModel.onItemSelected(selectedObject) // llamamos a la función del ViewModel y le pasamos el objeto seleccionado
                findNavController().navigate(R.id.action_homeFragment_to_characterDetailFragment, bundleOf(

                    "id" to selectedObject.id,
                    "name" to selectedObject.name,
                    "img" to selectedObject.image
                ))
            }

        })
    }

}