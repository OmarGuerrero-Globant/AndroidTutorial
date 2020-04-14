package com.example.rxjava.books

import com.example.rxjava.RestClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BooksPresenter {

    interface View {
        fun displayBooks(books: List<String>)
    }


    private lateinit var bookSubscription: Disposable
    private var view: View? = null

    fun onCreate(view : View){
        this.view = view
    }

    fun onDestroy(){
        this.view = null
    }

    fun initObservable(restClient : RestClient){
        val booksObservable: Observable<List<String>> =
            Observable.fromCallable { restClient.getFavoriteBooks() }
        bookSubscription =
            booksObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { strings -> view?.displayBooks(strings) }
    }

    fun cancelDisposable(){
        if (bookSubscription != null && !bookSubscription.isDisposed) {
            bookSubscription.dispose()
        }
    }
}