package com.example.rxjava.books

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjava.R
import com.example.rxjava.SimpleStringAdapter
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity(), BooksContract.View {
    private lateinit var stringAdapter: SimpleStringAdapter
    private val dataSource = DataSource()
    private val presenter = BooksPresenter(dataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        books_list.layoutManager = LinearLayoutManager(this)
        stringAdapter = SimpleStringAdapter(this)
        books_list.adapter = stringAdapter
        presenter.onCreate(this)
        presenter.initObservable()
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