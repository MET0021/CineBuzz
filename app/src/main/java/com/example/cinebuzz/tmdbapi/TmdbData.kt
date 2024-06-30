package com.example.cinebuzz.tmdbapi

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    val results: List<MovieData>,
    @SerializedName ("total_pages") val totalPage : Int,
)


data class MovieData (
    val adult : Boolean,
    val id : Int,
    val title : String,
    val overview : String,
    @SerializedName ("release_date") val date : String,
    @SerializedName ("vote_average") val note : Float,
    @SerializedName ("vote_count") val votes : Int,
    @SerializedName ("poster_path") val poster : String
)

data class MovieDetailData (
    val adult : Boolean,
    val id : Int,
    val title : String,
    val overview : String,
    @SerializedName ("poster_path") val poster : String,
    @SerializedName ("release_date") val date : String,
    @SerializedName ("vote_average") val note : Float,
    @SerializedName ("vote_count") val votes : Int,
)
