package com.example.cinebuzz

import android.app.Application
import com.example.cinebuzz.tmdbapi.TmdbService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App :Application() {

    companion object {
        lateinit var instance : App
        private const val BASE_URL = "https://api.themoviedb.org/3/"

//        val dataBase : DataBase by lazy {
//            DataBase(instance)
//        }

        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val tmdbService : TmdbService = retrofit.create(TmdbService::class.java)

    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}