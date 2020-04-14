package com.example.rxjava.colors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjava.R
import com.example.rxjava.SimpleStringAdapter
import kotlinx.android.synthetic.main.activity_colors.*


class ColorsActivity : AppCompatActivity() , ColorsPresenter.View {
    private val presenter =  ColorsPresenter()
    private lateinit var simpleStringAdapter: SimpleStringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(this)
        setContentView(R.layout.activity_colors)
        color_list.layoutManager = LinearLayoutManager(this)
        simpleStringAdapter = SimpleStringAdapter(this)
        color_list.adapter = simpleStringAdapter
        presenter.initDisposable()
    }

    override fun updateStringAdapter(colors: List<String>) {
        simpleStringAdapter.setStrings(colors)
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