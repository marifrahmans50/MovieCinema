package com.example.moviecinema.data

import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.domain.repository.IMovieCinemaRepository
import com.example.moviecinema.core.utils.DataMapper
import com.example.moviecinema.core.vo.Resource
import com.example.moviecinema.data.source.local.LocalDataSource
import com.example.moviecinema.data.source.remote.RemoteDataSource
import com.example.moviecinema.data.source.remote.response.ApiResponse
import com.example.moviecinema.data.source.remote.response.MovieResponse
import com.example.moviecinema.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieCinemaRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

) : IMovieCinemaRepository {

    override fun getAllMovies(sort: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(sort).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()


    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchMovie(title: String): Flow<List<Movie>> {
        return localDataSource.getSearchMovies(title).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }


    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute {
            localDataSource.setMovieFavorite(movieEntity, state)
        }
    }
}