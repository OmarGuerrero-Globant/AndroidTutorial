package com.example.rxjava.contract

import com.example.domain.dto.MovieDto

interface SimpleContract {

    interface View{
        fun onMovieLoaded(movieDto: MovieDto)
        fun onMovieLoadedFailed(message : String)
        fun onMoviesLoaded(list : List<MovieDto>)
        fun onMoviesLoadedFailed(message: String)
    }

    interface Presenter{
        fun getMovie(id: String)
        fun getMovieList(page : Int)
        fun onDestroy()
    }

}