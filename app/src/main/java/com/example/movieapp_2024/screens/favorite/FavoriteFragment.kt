package com.example.movieapp_2024.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp_2024.Constants
import com.example.movieapp_2024.R
import com.example.movieapp_2024.databinding.FragmentFavoriteBinding
import com.example.movieapp_2024.model.MovieResult
import com.example.movieapp_2024.screens.main.MainAdapter
import com.example.movieapp_2024.screens.main.MainAdapterDelegate
import com.example.movieapp_2024.screens.main.MainFragmentViewModel
import java.lang.ref.WeakReference

class FavoriteFragment : Fragment(), MainAdapterDelegate {
    private lateinit var binding: FragmentFavoriteBinding
    private var adapter: MainAdapter? = null
    private val viewModel by lazy {
        ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MainAdapter()
        adapter?.delegate = WeakReference(this)
        binding.rvFavorite.adapter = adapter

        viewModel.getAllMovies().observe(viewLifecycleOwner){
            adapter?.setList(it.asReversed())
        }
    }

    override fun onItemClick(model: MovieResult) {
        val bundle = Bundle()
        bundle.putSerializable("currentMovieModel", model)
        Constants.MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
    }
}