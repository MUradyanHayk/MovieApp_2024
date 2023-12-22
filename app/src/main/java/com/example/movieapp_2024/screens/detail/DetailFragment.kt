package com.example.movieapp_2024.screens.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp_2024.Constants
import com.example.movieapp_2024.R
import com.example.movieapp_2024.databinding.FragmentDetailBinding
import com.example.movieapp_2024.model.MovieResult

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var currentMovieModel: MovieResult? = null
    private var isFavorite = false
    private val viewModel by lazy {
        ViewModelProvider(this)[DetailFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovieModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("currentMovieModel", MovieResult::class.java)
        } else {
            arguments?.getSerializable("currentMovieModel") as? MovieResult?
        }

        binding.tvTitle.text = currentMovieModel?.title
        binding.tvDate.text = currentMovieModel?.release_date
        binding.tvDescription.text = currentMovieModel?.overview
        context?.let {
            Glide.with(it)
                .load(Constants.POSTER_PATH_BASE__URL + currentMovieModel?.poster_path)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.imgDetail)
        }

        isFavorite = currentMovieModel?.isFavorite ?: false
        if (!isFavorite) {
            binding.imgDetailFavorite.setBackgroundResource(R.drawable.baseline_favorite_border_24)
        } else {
            binding.imgDetailFavorite.setBackgroundResource(R.drawable.baseline_favorite_24)
        }

        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = !isFavorite
            if (!isFavorite) {
                binding.imgDetailFavorite.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                currentMovieModel?.let { movieModel -> viewModel.delete(movieModel) {} }
            } else {
                binding.imgDetailFavorite.setBackgroundResource(R.drawable.baseline_favorite_24)
                currentMovieModel?.isFavorite = true
                currentMovieModel?.let { movieModel -> viewModel.insert(movieModel) {} }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}