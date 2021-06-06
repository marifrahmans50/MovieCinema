package com.example.moviecinema.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviecinema.core.adapter.MovieAdapter
import com.example.moviecinema.favorite.StatusFavorite
import com.example.moviecinema.favorite.databinding.FragmentFavoriteBinding
import com.example.moviecinema.favorite.di.favoriteModule
import com.example.moviecinema.ui.detail.DetailMovie
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _favoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _favoriteBinding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _favoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        binding?.tvFavorite?.visibility = View.VISIBLE
        favoriteViewModel.getFavoriteMovie().observe(viewLifecycleOwner, { movies ->
            binding?.tvFavorite?.visibility = View.GONE
            if (movies.isNullOrEmpty()) {
                setDataState(StatusFavorite.BLANK)
            } else {
                setDataState(StatusFavorite.SUCCESS)
            }
            movieAdapter.setData(movies)
        })

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovie::class.java)
            intent.putExtra(DetailMovie.EXTRA_MOVIE_DATA, selectedData)
            startActivity(intent)
        }

        with(binding?.rvMovieFavorite) {
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    private fun setDataState(state: StatusFavorite) {
        when (state) {
            StatusFavorite.BLANK -> {
                binding?.tvFavorite?.visibility = View.VISIBLE
            }
            StatusFavorite.SUCCESS -> {
                binding?.tvFavorite?.visibility = View.GONE
            }
        }
    }
}