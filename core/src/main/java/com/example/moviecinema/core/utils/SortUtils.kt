package com.example.moviecinema.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val SORTBYNEWEST = "newest"
    const val SORTBYOLDEST = "oldest"
    const val SORTBYPOPULARITY = "popularity"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentities ")
        when (filter) {
            SORTBYNEWEST -> {
                simpleQuery.append("ORDER BY release DESC")
            }
            SORTBYOLDEST -> {
                simpleQuery.append("ORDER BY release ASC")
            }
            SORTBYPOPULARITY -> {
                simpleQuery.append("ORDER BY popularity DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}