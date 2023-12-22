package com.example.movieapp_2024.screens.favorite

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp_2024.data.room.MoviesRoomDatabase
import com.example.movieapp_2024.data.room.repository.RoomMoviesRepositoryImpl
import com.example.movieapp_2024.model.MovieResponse
import com.example.movieapp_2024.model.MovieResult
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel(val ctx: Application) : AndroidViewModel(ctx) {
    var repository = RoomMoviesRepositoryImpl(MoviesRoomDatabase.getInstance(ctx).getMovieDao())
    fun getAllMovies(): LiveData<List<MovieResult>> {
        return repository.allMovies
    }
}