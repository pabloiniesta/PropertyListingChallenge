package pablo.iniesta.propertylistingchallenge.presentation.propertylist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pablo.iniesta.propertylistingchallenge.R
import pablo.iniesta.propertylistingchallenge.data.db.PropertyEntity
import pablo.iniesta.propertylistingchallenge.databinding.FragmentPropertyListBinding
import pablo.iniesta.propertylistingchallenge.presentation.propertylist.adapters.PropertyAdapter
import pablo.iniesta.propertylistingchallenge.presentation.propertylist.adapters.PropertyListItemListener
import pablo.iniesta.propertylistingchallenge.util.Resource

@AndroidEntryPoint
class PropertyListFragment : Fragment(), PropertyListItemListener {

    private val viewModel: PropertyListViewModel by viewModels()
    private lateinit var binding: FragmentPropertyListBinding
    private lateinit var propertyAdapter: PropertyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPropertyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    override fun onItemClick(property: PropertyEntity) {
        navigateToPropertyDetails()
    }

    override fun onFavoriteClick(property: PropertyEntity) {
        viewModel.updateFavoritedProperty(property)
    }

    private fun setupObservers() {
        viewModel.properties.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { properties ->
                        Log.d("XXX", "SUCCESS LOADING: " + properties)
                        propertyAdapter.differ.submitList(properties)
                    }
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.d("XXX", "ERROR LOADING: " + message)
                    }
                }

                is Resource.Loading -> {
                    Log.d("XXX", "LOADING IN PROGRESS")
                }
            }
        }
    }

    private fun setupRecyclerView() {
        propertyAdapter = PropertyAdapter(this)
        binding.propertyRecyclerView.apply {
            adapter = propertyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun navigateToPropertyDetails() {
        findNavController().navigate(R.id.action_propertyListFragment_to_propertyDetailFragment)
    }
}