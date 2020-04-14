package com.example.rxjava.books

class BooksContract {

    interface View {
        fun displayBooks(books: List<String>)
    }

    interface Presenter {
        fun onCreate(view : BooksContract.View)
        fun onDestroy()
        fun initObservable()
        fun cancelDisposable()
    }

}