package com.example.movieapp_2024.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp_2024.data.room.MoviesRoomDatabase
import com.example.movieapp_2024.data.room.repository.RoomMoviesRepositoryImpl
import com.example.movieapp_2024.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragmentViewModel(val ctx: Application) : AndroidViewModel(ctx) {
    var repository = RoomMoviesRepositoryImpl(MoviesRoomDatabase.getInstance(ctx).getMovieDao())
    fun insert(movieResult: MovieResult, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(movieResult, onSuccess)
        }
    }

    fun delete(movieResult: MovieResult, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(movieResult, onSuccess)
        }
    }
}