package com.example.moviecinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.usecase.MovieCinemaUseCase
import com.example.moviecinema.core.vo.Resource

class HomeViewModel(private val movieUseCase: MovieCinemaUseCase) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovies(sort).asLiveData()
}