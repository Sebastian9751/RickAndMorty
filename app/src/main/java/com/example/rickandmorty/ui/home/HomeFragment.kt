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
import com.example.rickandmorty.databinding.FragmentHomeBinding
import com.example.rickandmorty.domain.GetCharacterUseCase
import com.example.rickandmorty.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val result = GetCharacterUseCase()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.swipe.isEnabled =false
        lifecycleScope.launch {
            binding.swipe.isRefreshing =true
            val response = result.invoke()
            response?.results?.let { results ->
                val adapter = HomeAdapter(results) { ch -> onItemSelect(ch) }
                val manager = LinearLayoutManager(requireContext())
                val decoration = DividerItemDecoration(requireContext(), manager.orientation)
                binding.recyclerView.layoutManager = manager
                binding.recyclerView.adapter = adapter
                binding.recyclerView.addItemDecoration(decoration)
            }
            binding.swipe.isRefreshing =false
            Log.i("hellooRk", "$response")
        }
    }



    private fun onItemSelect(characterss: ResultsModel) {
        val direction =
            HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(characterss)
        findNavController().navigate(direction)
    }
}
