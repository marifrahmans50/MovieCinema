package com.example.moviecinema.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviecinema.data.source.local.entity.MovieData

@Database(
    entities = [MovieData::class],
    version = 1,
    exportSchema = false
)

abstract class MovieCinemaDatabase : RoomDatabase() {

    abstract fun movieCinemaDao(): MovieCinemaDao

}