package com.example.movieapp_2024.data.room.repository

import androidx.lifecycle.LiveData
import com.example.movieapp_2024.model.MovieResult

interface RoomMoviesRepository {
    val allMovies: LiveData<List<MovieResult>>
    suspend fun insertMovie(movieResult: MovieResult, onSuccess: () -> Unit)
    suspend fun deleteMovie(movieResult: MovieResult, onSuccess: () -> Unit)
    suspend fun getMovieById(id: Int):MovieResult?
}