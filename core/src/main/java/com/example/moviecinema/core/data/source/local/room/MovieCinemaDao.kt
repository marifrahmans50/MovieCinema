package com.example.moviecinema.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.moviecinema.data.source.local.entity.MovieData
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCinemaDao {

    @RawQuery(observedEntities = [MovieData::class])
    fun getAllMovies(query: SupportSQLiteQuery): Flow<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataMovie(movies: List<MovieData>)

    @Update
    fun updateDataMovie(movies: MovieData)


    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getFavoriteMovies(): Flow<List<MovieData>>

    @Query("SELECT * FROM movieentities where title like :title")
    fun getSearchResult(title: String): Flow<List<MovieData>>

}