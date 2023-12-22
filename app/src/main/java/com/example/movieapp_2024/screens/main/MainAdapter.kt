package com.example.movieapp_2024.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp_2024.Constants
import com.example.movieapp_2024.R
import com.example.movieapp_2024.databinding.ItemLayoutBinding
import com.example.movieapp_2024.model.MovieResult
import java.lang.ref.WeakReference

interface MainAdapterDelegate {
    fun onItemClick(model: MovieResult)
}

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {
    var delegate: WeakReference<MainAdapterDelegate>? = null

    class MainAdapterViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var movies = emptyList<MovieResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        holder.binding.itemTitle.text = movies[position].title
        holder.binding.itemDate.text = movies[position].release_date
        holder.binding.root.setOnClickListener {
            delegate?.get()?.onItemClick(movies[position])
        }
        Glide.with(holder.binding.root.context)
            .load(Constants.POSTER_PATH_BASE__URL + movies[position].poster_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.binding.itemImg)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(movies: List<MovieResult>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}