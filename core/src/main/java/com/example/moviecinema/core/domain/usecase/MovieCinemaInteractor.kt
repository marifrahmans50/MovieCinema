package com.example.moviecinema.core.domain.usecase

import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.repository.IMovieCinemaRepository
import com.example.moviecinema.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieCinemaInteractor(private val iMovieCinemaRepository: IMovieCinemaRepository) :
    MovieCinemaUseCase {

    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> {
        return iMovieCinemaRepository.getAllMovies(sort)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return iMovieCinemaRepository.getFavoriteMovies()
    }

    override fun getSearchMovies(title: String): Flow<List<Movie>> {
        return iMovieCinemaRepository.getSearchMovie(title)
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        return iMovieCinemaRepository.setMovieFavorite(movie, state)
    }
}