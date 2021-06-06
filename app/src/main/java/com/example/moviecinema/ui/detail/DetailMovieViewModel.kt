package com.example.moviecinema.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.usecase.MovieCinemaUseCase


class DetailMovieViewModel(private val movieUseCase: MovieCinemaUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        movieUseCase.setMovieFavorite(movie, newState)
    }
}
