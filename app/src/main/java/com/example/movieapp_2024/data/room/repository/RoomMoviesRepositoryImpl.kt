package com.example.movieapp_2024.data.room.repository

import androidx.lifecycle.LiveData
import com.example.movieapp_2024.data.room.dao.MoviesDao
import com.example.movieapp_2024.model.MovieResult

class RoomMoviesRepositoryImpl(private val moviesDao: MoviesDao) : RoomMoviesRepository {
    override val allMovies: LiveData<List<MovieResult>>
        get() = moviesDao.getAllMovies()

    override suspend fun insertMovie(movieResult: MovieResult, onSuccess: () -> Unit) {
        moviesDao.insert(movieResult)
        onSuccess()
    }

    override suspend fun deleteMovie(movieResult: MovieResult, onSuccess: () -> Unit) {
        moviesDao.delete(movieResult)
        onSuccess()
    }

    override suspend fun getMovieById(id: Int): MovieResult? {
        return moviesDao.getMovieById(id)
    }
}