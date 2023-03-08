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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.models.ResultsModel
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.ui.home.HomeViewModel
import com.example.rickandmorty.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val result = GetCharacterUseCase()
    private val characterList = mutableListOf<ResultsModel>()
    private lateinit var  viewModel : HomeViewModel
    private var PAGUE = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel = HomeViewModel()
        binding.swipe.isEnabled = false
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
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
                    binding.recyclerView.scrollToPosition(viewModel.currentVisiblePosition)
                }
            }
        })


    }

    private fun setRecyclerView() {
        lifecycleScope.launch {
            binding.swipe.isRefreshing = true
            // guardar la posición actual del reciclador en el ViewModel
            viewModel.currentVisiblePosition =
                (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
            val response = result.invoke(PAGUE)
            response?.results?.let { results ->
                characterList.addAll(results)
                val adapter = HomeAdapter(characterList) { ch -> onItemSelect(ch) }
                binding.recyclerView.adapter = adapter
                // restaurar la posición del reciclador desde el ViewModel después de actualizar el conjunto de datos
            }

            binding.recyclerView.scrollToPosition(viewModel.currentVisiblePosition)

            binding.swipe.isRefreshing = false
            Log.i("hellooRk", "$response")
        }
    }

    private fun onItemSelect(characterss: ResultsModel) {
        val direction =
            HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(characterss)
        findNavController().navigate(direction)
    }
}


