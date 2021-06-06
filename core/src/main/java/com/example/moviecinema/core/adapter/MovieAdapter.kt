package com.example.moviecinema.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecinema.core.R
import com.example.moviecinema.core.databinding.ItemCardviewMovieBinding
import com.example.moviecinema.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MovieViewHolder {
        val itemListBinding = ItemCardviewMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(private val binding: ItemCardviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.title
                tvOriginalLanguage.text = movie.originalLanguage
                tvRating.text = movie.rating
                tvRelease.text = movie.release

                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.image_url, movie.poster))
                    .apply {
                        RequestOptions()
                            .placeholder(R.drawable.ic_baseline_broken_image_24)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    }
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
/*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemCardviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(private val binding: ItemCardviewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.title
                tvOriginalLanguage.text = movie.originalLanguage
                tvRating.text = movie.rating
                tvRelease.text = movie.release

                val circularProgress = CircularProgressDrawable(itemView.context)
                circularProgress.strokeWidth = 16f
                circularProgress.centerRadius = 64f
                circularProgress.start()

                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.image_url, movie.poster))
                    .apply(RequestOptions.placeholderOf(circularProgress))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imgPoster)

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

}

 */