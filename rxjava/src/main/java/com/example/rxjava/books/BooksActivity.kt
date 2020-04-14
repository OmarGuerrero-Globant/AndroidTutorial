package com.example.rxjava.books

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjava.R
import com.example.rxjava.RestClient
import com.example.rxjava.SimpleStringAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {
    private lateinit var bookSubscription: Disposable
    private lateinit var stringAdapter: SimpleStringAdapter
    private lateinit var restClient: RestClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restClient = RestClient(this)
        configureLayout()
        createObservable()
    }

    private fun createObservable() {
        val booksObservable: Observable<List<String>> =
            Observable.fromCallable { restClient.getFavoriteBooks() }
        bookSubscription =
            booksObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { strings -> displayBooks(strings) }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (bookSubscription != null && !bookSubscription.isDisposed) {
            bookSubscription.dispose()
        }
    }

    private fun displayBooks(books: List<String>) {
        stringAdapter.setStrings(books)
        loader.visibility = View.GONE
        books_list.visibility = View.VISIBLE
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_books)
        books_list.layoutManager = LinearLayoutManager(this)
        stringAdapter = SimpleStringAdapter(this)
        books_list.adapter = stringAdapter
    }

    override fun onStop() {
        super.onStop()
        if (bookSubscription != null && !bookSubscription.isDisposed) {
            bookSubscription.dispose()
        }
    }
}