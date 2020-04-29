package com.example.rxjava.presenter

import com.example.domain.providers.MoviesProvider
import com.example.domain.usecases.movies.GetMovieByIdUseCase
import com.example.domain.usecases.movies.GetMoviesUseCase
import com.example.rxjava.contract.SimpleContract
import io.reactivex.disposables.CompositeDisposable

class SimplePresenter(private val moviesProvider: MoviesProvider,
                      private val view : SimpleContract.View
)  : SimpleContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getMovie(id: String) {
        val params = GetMovieByIdUseCase.Params(id)
        disposable.add(moviesProvider.getMovieByIdUseCase().execute(params)
            .subscribe({ success ->
                view.onMovieLoaded(success)
            }, {error ->
                view.onMovieLoadedFailed(error.message ?: "Movie Error")
            })
        )
    }

    override fun getMovieList(page : Int) {
        val params = GetMoviesUseCase.Params(page)
        disposable.add(moviesProvider.getMoviesUseCase().execute(params)
            .subscribe({success ->
                view.onMoviesLoaded(success)
            }, {error ->
                view.onMoviesLoadedFailed(error.message ?: "Movies error")
        })
        )
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}