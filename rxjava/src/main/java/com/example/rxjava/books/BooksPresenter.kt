package com.example.rxjava.books

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BooksPresenter(private val dataSource: DataSource) : BooksContract.Presenter {
    private lateinit var bookSubscription: Disposable
    private var view: BooksContract.View? = null

    override fun onCreate(view : BooksContract.View){
        this.view = view
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun initObservable(){
        val booksObservable: Observable<List<String>> =
            Observable.fromCallable { dataSource.getFavoriteBooks()}
        bookSubscription =
            booksObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { strings -> view?.displayBooks(strings) }
    }

    override fun cancelDisposable(){
        if (bookSubscription != null && !bookSubscription.isDisposed) {
            bookSubscription.dispose()
        }
    }
}