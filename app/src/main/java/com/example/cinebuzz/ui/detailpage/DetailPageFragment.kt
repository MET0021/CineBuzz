package com.example.cinebuzz.ui.detailpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.cinebuzz.R
import com.example.cinebuzz.databinding.DetailPageFragmentBinding
import com.example.cinebuzz.ui.home.MovieAdapter
import com.example.cinebuzz.viewModel.MovieDetailViewModel

class DetailPageFragment : Fragment(){

    private var _binding: DetailPageFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    companion object {

        val MOVIE_ID = "MOVIE_ID"
        fun newInstance()= DetailPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailPageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity?.intent!!.hasExtra(MOVIE_ID)){
            requireActivity().intent.getIntExtra(MOVIE_ID,0).let {
                getMovieDetail(it)
            }

        }
    }

    private fun getMovieDetail(movieId: Int?) {
        if (movieId != null) {
            movieDetailViewModel.getMoviesDetails(movieId)
        }
        movieDetailViewModel.movie.observe(viewLifecycleOwner) { movieDetail ->
            binding.movieTitle.text = movieDetail?.title
            binding.releaseDate.text = movieDetail?.date
            binding.overview.text = movieDetail?.overview
            binding.moviePoster
                .load("https://image.tmdb.org/t/p/w500${movieDetail?.poster}"){
                    placeholder(R.drawable.ic_launcher_foreground)
                }
        }
    }
}