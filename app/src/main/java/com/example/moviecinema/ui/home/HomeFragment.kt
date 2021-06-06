package com.example.moviecinema.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviecinema.core.adapter.MovieAdapter
import com.example.moviecinema.core.domain.model.Movie
import com.example.moviecinema.core.utils.SortUtils
import com.example.moviecinema.core.vo.Resource
import com.example.moviecinema.databinding.FragmentHomeBinding
import com.example.moviecinema.ui.detail.DetailMovie
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()
    private val moviesAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setList(SortUtils.SORTBYNEWEST)

        moviesAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovie::class.java)
            intent.putExtra(DetailMovie.EXTRA_MOVIE_DATA, selectedData)
            startActivity(intent)
        }

        binding?.progressBar?.visibility = View.VISIBLE
        with(binding?.rvMovie) {
            this?.setHasFixedSize(true)
            this?.adapter = moviesAdapter
        }

        binding?.buttonNewest?.setOnClickListener { setList(SortUtils.SORTBYNEWEST) }
        binding?.buttonOldest?.setOnClickListener { setList(SortUtils.SORTBYOLDEST) }
        binding?.buttonHighPopularity?.setOnClickListener { setList(SortUtils.SORTBYPOPULARITY) }
    }

    private fun setList(sort: String) {
        homeViewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<Resource<List<Movie>>> { movies ->
        if (movies != null) {
            when (movies) {
                is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    moviesAdapter.setData(movies.data)
                    moviesAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "There is an Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentHomeBinding = null
    }
}