package com.example.moviecinema.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.usecase.MovieCinemaUseCase

class SearchViewModel(private val movieUseCase: MovieCinemaUseCase) : ViewModel() {

    fun searchForItems(title: String): LiveData<List<Movie>> {
        return movieUseCase.getSearchMovies(title).asLiveData()
    }
}