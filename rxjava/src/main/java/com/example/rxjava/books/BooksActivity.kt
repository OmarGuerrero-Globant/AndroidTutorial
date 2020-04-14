package com.example.rxjava.books

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjava.R
import com.example.rxjava.RestClient
import com.example.rxjava.SimpleStringAdapter
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity(), BooksPresenter.View {
    private val presenter = BooksPresenter()
    private lateinit var stringAdapter: SimpleStringAdapter
    private lateinit var restClient: RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        books_list.layoutManager = LinearLayoutManager(this)
        stringAdapter = SimpleStringAdapter(this)
        books_list.adapter = stringAdapter
        restClient = RestClient(this)
        presenter.onCreate(this)
        presenter.initObservable(restClient)
    }

    override fun displayBooks(books: List<String>) {
        stringAdapter.setStrings(books)
        loader.visibility = View.GONE
        books_list.visibility = View.VISIBLE
    }

    override fun onStop() {
        presenter.cancelDisposable()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }


}