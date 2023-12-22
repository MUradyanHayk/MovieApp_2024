package com.example.movieapp_2024.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp_2024.model.MovieResult

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieResult: MovieResult)

    @Delete
    suspend fun delete(movieResult: MovieResult)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MovieResult>>

    @Query("SELECT * FROM movie_table WHERE id=:id")
    fun getMovieById(id: Int): MovieResult?

}