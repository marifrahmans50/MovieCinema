package com.example.moviecinema.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.usecase.MovieCinemaUseCase

class FavoriteViewModel(private val movieUseCase: MovieCinemaUseCase) : ViewModel() {

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        movieUseCase.getFavoriteMovies().asLiveData()

}