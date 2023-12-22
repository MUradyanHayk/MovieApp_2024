package com.example.movieapp_2024.data.retrofit.api

import com.example.movieapp_2024.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {
    @GET("3/movie/popular?api_key=d7208cee460231fc7f8afb079ae2abbf&language=en-US&page1")
    suspend fun getPopularMovie(): Response<MovieResponse>
}