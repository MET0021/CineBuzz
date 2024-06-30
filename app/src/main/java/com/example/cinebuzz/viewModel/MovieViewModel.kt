package com.example.cinebuzz.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinebuzz.App
import com.example.cinebuzz.tmdbapi.MovieData
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class MovieViewModel : ViewModel() {

    private val TAG = MovieViewModel::class.java.simpleName

    private val _movies = MutableLiveData<List<MovieData>>()
    val movies : LiveData<List<MovieData>> get() = _movies
    private var currentPage = 1
    private var totalPages = 1

    fun getMoviesList(page : Int){
        viewModelScope.launch {
            try {
                val response = App.tmdbService.getMovieList(page = page).awaitResponse()
                if (response.isSuccessful) {
                    response.body()?.let {movieResponse ->
                        _movies.postValue(movieResponse.results)
                        totalPages = movieResponse.totalPage
                    }
                } else {
                    Log.e(TAG, "Error : ${response.message()}")
                }
            } catch (e :Exception){
                Log.e(TAG, "getMoviesList Error : ${e.message}", )
            }

        }
    }

    fun loadNextPage(){
        if (currentPage < totalPages){
            currentPage++
            getMoviesList(currentPage)
        }
    }
}