package com.example.cinebuzz.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinebuzz.App
import com.example.cinebuzz.tmdbapi.MovieData
import com.example.cinebuzz.tmdbapi.MovieDetailData
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MovieDetailViewModel : ViewModel() {

    private val TAG = MovieDetailViewModel::class.java.simpleName
    private val _movie = MutableLiveData<MovieDetailData?>()
    val movie: LiveData<MovieDetailData?> = _movie

    fun getMoviesDetails(movieId : Int) : MovieDetailData? {
        Log.e(TAG, "getMoviesDetails: $movieId", )
        viewModelScope.launch {
            try {
                val response = App.tmdbService.getMovieDetails(movieId = movieId).awaitResponse()
                if (response.isSuccessful) {
                    response.body().let {movieResponse ->
                        _movie.postValue(movieResponse)
                    }
                } else {
                    Log.e(TAG, "Error : ${response.message()}")
                }
            } catch (e :Exception){
                Log.e(TAG, "getMoviesList Error : ${e.message}", )
            }

        }
        return _movie.value
    }

}