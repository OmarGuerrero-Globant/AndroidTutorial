package com.example.data

import com.example.data.remote.services.MoviesServices
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import repositories.MovieDataRepository
import kotlin.properties.Delegates

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MovieDataRepositoryTest {

    private var mockPage : Int by Delegates.notNull<Int>()
    private lateinit var  message : String

    @Mock
    private lateinit var moviesServices: MoviesServices

    private val movieDataRepository : MovieDataRepository by lazy {
        MovieDataRepository(moviesServices)
    }

    @Before
    fun setUp(){
        mockPage = 1
        message = "Not Found"
    }


    @Test
    fun validateGetMovieError(){
        Mockito.`when`(moviesServices.getMovies(1)).thenReturn(Single.error(Throwable(message)))
        movieDataRepository.getMovies(mockPage)
            .test()
            .assertError{
                it.message == message
            }
            .assertNotComplete()
    }

    @Test
    fun validateGetMovieByIdError(){
        Mockito.`when`(moviesServices.getMovieById(1)).thenReturn(Single.error(Throwable(message)))
        movieDataRepository.getMovieById("1")
            .test()
            .assertError {
                it.message == message
            }
            .assertNotComplete()
    }
}