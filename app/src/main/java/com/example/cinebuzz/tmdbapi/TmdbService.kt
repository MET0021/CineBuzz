package com.example.cinebuzz.tmdbapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
interface TmdbService {

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc")
    fun getMovieList(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
    ) : Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ) : Call<MovieDetailData>
}