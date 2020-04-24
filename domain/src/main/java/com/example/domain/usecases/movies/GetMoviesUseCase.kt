package com.example.domain.usecases.movies

import com.example.domain.dto.MovieDto
import com.example.domain.executors.PostExecutionThread
import com.example.domain.executors.ThreadExecutor
import com.example.domain.repository.MoviesRepository
import com.example.domain.usecases.GroupUseCase
import io.reactivex.Single

class GetMoviesUseCase(private val moviesRepository: MoviesRepository,
                       threadExecutor: ThreadExecutor,
                       postExecutionThread: PostExecutionThread
) : GroupUseCase<List<MovieDto>>(threadExecutor, postExecutionThread) {

    override fun buildGroupUseCase(): Single<List<MovieDto>> {
        return moviesRepository.getMovies()
    }


}