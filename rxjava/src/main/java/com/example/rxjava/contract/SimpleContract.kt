package com.example.rxjava.contract

import com.example.domain.dto.MovieDto

interface SimpleContract {

    interface View{
        fun onMovieLoaded(movieDto: MovieDto)
        fun onMovieLoadedFailed(message : String)
        fun onMoviesLoaded(list : List<MovieDto>)
        fun onMoviesLoadedFailed() : String
    }

    interface Presenter{
        fun getMovie(id: String)
        fun getMovieList()
        fun onDestroy()
    }

}