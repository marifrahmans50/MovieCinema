package com.example.moviecinema.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.moviecinema.core.adapter.MovieAdapter
import com.example.moviecinema.databinding.FragmentSearchBinding
import com.example.moviecinema.ui.detail.DetailMovie
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()
    private var _searchBinding: FragmentSearchBinding? = null
    private val binding get() = _searchBinding


    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _searchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMovie?.adapter = movieAdapter

        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailMovie::class.java)
            intent.putExtra(DetailMovie.EXTRA_MOVIE_DATA, selectedData)
            startActivity(intent)
        }

        binding?.searchViewMovie?.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        getItemFromDb(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        getItemFromDb(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun getItemFromDb(searchText: String) {
        var text = searchText
        text = "%$text%"

        searchViewModel.searchForItems(title = text).observe(this, { list ->
            list?.let {
                movieAdapter.setData(list)
                binding?.text?.visibility = View.GONE
            }
        })
    }
}