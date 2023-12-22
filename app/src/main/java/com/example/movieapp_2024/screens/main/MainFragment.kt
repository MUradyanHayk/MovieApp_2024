package com.example.movieapp_2024.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp_2024.Constants
import com.example.movieapp_2024.R
import com.example.movieapp_2024.databinding.FragmentMainBinding
import com.example.movieapp_2024.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class MainFragment : Fragment(), MainAdapterDelegate {
    private lateinit var binding: FragmentMainBinding
    private var adapter: MainAdapter? = null
    private val viewModel by lazy {
        ViewModelProvider(this)[MainFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter()
        adapter?.delegate = WeakReference(this)
        binding.rvMain.adapter = adapter
        viewModel.getMovies()
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            response.body()?.results?.let { it ->
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    adapter?.setList(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                Constants.MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(model: MovieResult) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            val movie = viewModel.getMovieById(model.id)
            val bundle = Bundle()
            if (movie != null) {
                model.isFavorite = true
            }
            bundle.putSerializable("currentMovieModel", model)
            viewModel.viewModelScope.launch(Dispatchers.Main) {
                Constants.MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            }
        }
    }
}