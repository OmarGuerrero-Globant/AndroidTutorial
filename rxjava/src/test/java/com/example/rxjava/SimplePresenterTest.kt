package com.example.rxjava

import com.example.domain.dto.MovieDto
import com.example.domain.providers.MoviesProvider
import com.example.rxjava.contract.SimpleContract
import com.example.rxjava.presenter.SimplePresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.properties.Delegates

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class SimplePresenterTest {

    private lateinit var id  : String
    private var page : Int by Delegates.notNull()

    private val simplePresenter : SimplePresenter by lazy {
        SimplePresenter(moviesProvider, view)
    }

    @Mock
    private lateinit var moviesProvider : MoviesProvider
    private lateinit var view: SimpleContract.View

    @Before
    fun setUp(){
        id = "1"
        page = 1
    }

    @Test
    fun validateGetMovieSuccess(){
        Mockito.verify(view).onMovieLoaded(Mockito.any(MovieDto::class.java))
        simplePresenter.getMovie(id)
    }

    @Test
    fun validateGetMovieError(){
        Mockito.verify(view).onMovieLoadedFailed(Mockito.anyString())
        simplePresenter.getMovie(id)
    }

    @Test
    fun validateGetMovieListSuccess(){
        Mockito.verify(view).onMoviesLoaded(Mockito.anyListOf(MovieDto::class.java))
        simplePresenter.getMovieList(1)
    }

    @Test
    fun validateGetMovieListError(){
        Mockito.verify(view).onMoviesLoadedFailed(Mockito.anyString())
        simplePresenter.getMovieList(1)
    }
}