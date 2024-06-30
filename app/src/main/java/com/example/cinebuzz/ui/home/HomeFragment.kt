package com.example.cinebuzz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinebuzz.databinding.HomeFragmentBinding
import com.example.cinebuzz.tmdbapi.MovieData
import com.example.cinebuzz.viewModel.MovieViewModel

class HomeFragment : Fragment() {


    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val grid =GridLayoutManager(this@HomeFragment.requireContext(), 2);
        binding.moviesList.layoutManager = grid
        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter = MovieAdapter(movies as MutableList<MovieData>?)
            binding.moviesList.adapter = movieAdapter
        }
        movieViewModel.getMoviesList(1)

        binding.moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    movieViewModel.loadNextPage()
                }
            }
        })
        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.upadateMovieList(movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}