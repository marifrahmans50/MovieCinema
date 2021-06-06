package com.example.moviecinema.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecinema.R
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovie : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_DATA = "extra_movie_data"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE_DATA)
        if (detailMovie != null) {
            loadDataDetail(detailMovie)
        }
    }

    private fun loadDataDetail(movies: Movie) {

        with(binding) {
            tvTitle.text = movies.title
            tvOriginalLanguage.text = movies.originalLanguage
            tvPopularity.text = movies.popularity
            tvRating.text = movies.rating
            tvRelease.text = movies.release
            tvOverview.text = movies.overview

            var status = movies.favorited
            setFavorite(status)

            favButton.setOnClickListener {
                status = !status
                viewModel.setFavoriteMovie(movies, status)
                setFavorite(status)
            }

            Glide.with(this@DetailMovie)
                .load(this@DetailMovie.getString(R.string.image_url, movies.poster))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imgPoster)

        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.favButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.favButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

}