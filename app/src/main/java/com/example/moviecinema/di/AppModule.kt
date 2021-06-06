package com.example.moviecinema.di

import com.example.moviecinema.core.domain.usecase.MovieCinemaInteractor
import com.example.moviecinema.core.domain.usecase.MovieCinemaUseCase
import com.example.moviecinema.ui.detail.DetailMovieViewModel
import com.example.moviecinema.ui.home.HomeViewModel
import com.example.moviecinema.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieCinemaUseCase> { MovieCinemaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}