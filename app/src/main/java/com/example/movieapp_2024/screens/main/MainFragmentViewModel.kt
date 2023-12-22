package com.example.movieapp_2024.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp_2024.data.retrofit.RetrofitRepository
import com.example.movieapp_2024.data.room.MoviesRoomDatabase
import com.example.movieapp_2024.data.room.repository.RoomMoviesRepositoryImpl
import com.example.movieapp_2024.model.MovieResponse
import com.example.movieapp_2024.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(val ctx: Application) : AndroidViewModel(ctx) {
    val retrofitRepository = RetrofitRepository()
    val roomRepository = RoomMoviesRepositoryImpl(MoviesRoomDatabase.getInstance(ctx).getMovieDao())
    val movies: MutableLiveData<Response<MovieResponse>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            movies.value = retrofitRepository.getMovies()
        }
    }


    suspend fun getMovieById(id: Int): MovieResult? {
            return roomRepository.getMovieById(id)
    }
}