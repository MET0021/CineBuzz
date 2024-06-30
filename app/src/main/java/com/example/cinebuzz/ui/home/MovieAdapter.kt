package com.example.cinebuzz.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cinebuzz.R
import com.example.cinebuzz.databinding.MovieItemBinding
import com.example.cinebuzz.tmdbapi.MovieData
import com.example.cinebuzz.ui.detailpage.DetailPageActivity
import com.example.cinebuzz.ui.detailpage.DetailPageFragment


class MovieAdapter(
    private val movies: MutableList<MovieData>?,
    ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie : MovieData){
            binding.movieTitle.text = movie.title
            binding.releaseDate.text = movie.date
            binding.moviePoster
                .load("https://image.tmdb.org/t/p/w500${movie.poster}"){
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            binding.cardView.setOnClickListener {
                val intent = Intent(binding.root.context, DetailPageActivity::class.java)
                intent.putExtra(DetailPageFragment.MOVIE_ID,movie.id)
                binding.root.context.startActivity(intent)
            }
        }

    }

    fun upadateMovieList(newMovies: List<MovieData>) {
        movies?.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int {
        return movies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies?.get(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }


}