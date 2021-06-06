package com.example.moviecinema.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: String,
    var title: String,
    var overview: String,
    var originalLanguage: String,
    var rating: String,
    var popularity: String,
    var release: String,
    var poster: String,
    var favorited: Boolean
) : Parcelable