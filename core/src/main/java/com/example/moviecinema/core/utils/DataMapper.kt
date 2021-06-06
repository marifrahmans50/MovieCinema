package com.example.moviecinema.core.utils

import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.data.source.local.entity.MovieData
import com.example.moviecinema.data.source.remote.response.MovieResponse

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieData> {
        val movieList = ArrayList<MovieData>()
        input.map {
            val movie = MovieData(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                rating = it.rating,
                popularity = it.popularity,
                release = it.release,
                poster = it.poster,
                favorited = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieData>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                rating = it.rating,
                popularity = it.popularity,
                release = it.release,
                poster = it.poster,
                favorited = it.favorited
            )
        }

    fun mapDomainToEntities(input: Movie): MovieData {
        return MovieData(
            input.id,
            input.title,
            input.overview,
            input.originalLanguage,
            input.rating,
            input.popularity,
            input.release,
            input.poster,
            input.favorited
        )
    }

}
