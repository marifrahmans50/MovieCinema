package com.example.moviecinema.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieData(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "popularity")
    var popularity: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)