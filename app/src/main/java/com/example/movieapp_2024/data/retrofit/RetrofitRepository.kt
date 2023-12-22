package com.example.movieapp_2024.data.retrofit

import com.example.movieapp_2024.data.retrofit.api.RetrofitInstance
import com.example.movieapp_2024.model.MovieResponse
import com.example.movieapp_2024.model.MovieResult
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MovieResponse> {
        return RetrofitInstance.api.getPopularMovie()
    }
}