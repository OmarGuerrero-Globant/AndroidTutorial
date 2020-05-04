package com.example.rxjava

import com.example.data.executors.JobExecutor
import com.example.data.executors.UIThread
import com.example.data.providers.MoviesDataProvider
import com.example.data.remote.services.MoviesServices
import com.example.data.repositories.MovieDataRepository
import com.example.domain.executors.PostExecutionThread
import com.example.domain.executors.ThreadExecutor
import com.example.domain.providers.MoviesProvider
import com.example.domain.repository.MoviesRepository
import com.example.rxjava.contract.SimpleContract
import com.example.rxjava.presenter.SimplePresenter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SimpleRegistry {

    companion object {
        const val API_URL = "https://api.themoviedb.org/3/"
    }

    private val threadExecutor: ThreadExecutor by lazy {
        JobExecutor()
    }

    private val postThreadExecutor : PostExecutionThread by lazy {
        UIThread()
    }

    private fun getMoviesServices() : MoviesServices{
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return  retrofit.create(MoviesServices::class.java)
    }

    private val moviesRepository : MoviesRepository by lazy {
        MovieDataRepository(getMoviesServices())
    }

    private val moviesProvider : MoviesProvider by lazy {
        MoviesDataProvider(moviesRepository, threadExecutor, postThreadExecutor)
    }

    fun provide(view: SimpleContract.View) : SimpleContract.Presenter{
        return SimplePresenter(moviesProvider , view)
    }

}