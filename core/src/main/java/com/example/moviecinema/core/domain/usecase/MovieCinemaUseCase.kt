package com.example.moviecinema.core.domain.usecase

import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieCinemaUseCase {

    fun getAllMovies(sort: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getSearchMovies(title: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}