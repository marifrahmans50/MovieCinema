package com.example.moviecinema.data.source.local

import com.example.moviecinema.core.utils.SortUtils
import com.example.moviecinema.data.source.local.entity.MovieData
import com.example.moviecinema.data.source.local.room.MovieCinemaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val mMovieCinemaDao: MovieCinemaDao) {
    fun getAllMovies(sort: String): Flow<List<MovieData>> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieCinemaDao.getAllMovies(query)
    }

    suspend fun insertMovie(movies: List<MovieData>) = mMovieCinemaDao.insertDataMovie(movies)

    fun getAllFavoriteMovies(): Flow<List<MovieData>> {
        return mMovieCinemaDao.getFavoriteMovies()
    }

    fun setMovieFavorite(movie: MovieData, newState: Boolean) {
        movie.favorited = newState
        mMovieCinemaDao.updateDataMovie(movie)
    }

    fun getSearchMovies(title: String): Flow<List<MovieData>> {
        return mMovieCinemaDao.getSearchResult(title)
    }

}