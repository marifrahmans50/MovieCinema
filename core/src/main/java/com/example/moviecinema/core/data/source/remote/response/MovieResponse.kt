package com.example.moviecinema.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("original_language")
    var originalLanguage: String,

    @field:SerializedName("vote_average")
    var rating: String,

    @field:SerializedName("popularity")
    var popularity: String,

    @field:SerializedName("release_date")
    var release: String,

    @field:SerializedName("poster_path")
    var poster: String,

    @field:SerializedName("favorited")
    var favorited: Boolean

)