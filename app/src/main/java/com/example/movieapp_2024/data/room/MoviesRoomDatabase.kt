package com.example.movieapp_2024.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp_2024.data.room.dao.MoviesDao
import com.example.movieapp_2024.model.MovieResult

@Database(entities = [MovieResult::class], version = 2)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MoviesDao

    companion object {
        private var database: MoviesRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MoviesRoomDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, MoviesRoomDatabase::class.java, "movie_db").build()
            }
            return database as MoviesRoomDatabase
        }
    }
}