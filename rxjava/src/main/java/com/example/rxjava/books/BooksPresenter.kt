package com.example.rxjava.books

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

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