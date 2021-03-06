package com.example.domain.repository

import com.example.domain.dto.MovieDto
import io.reactivex.Single

interface MoviesRepository {
    fun getMovieById(id : String) : Single<MovieDto>
    fun getMovies(page : Int) : Single<List<MovieDto>>
}