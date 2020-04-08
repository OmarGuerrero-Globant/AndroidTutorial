package com.example.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_colors.*


class ColorsActivity : AppCompatActivity() {
    private lateinit var simpleStringAdapter: SimpleStringAdapter
    private lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
    }

    companion object {
        private fun getColorList() : List<String> {
                val colors: ArrayList<String> = ArrayList()
                with(colors) {
                    add("red")
                    add("green")
                    add("blue")
                    add("pink")
                    add("brown")
                }
                return colors
        }
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_colors)
        color_list.layoutManager = LinearLayoutManager(this)
        simpleStringAdapter = SimpleStringAdapter(this)
        color_list.adapter = simpleStringAdapter
    }

    private fun createObservable() {
        val listObservable: Observable<List<String>> =
            Observable.just(getColorList())
        disposable =
            listObservable.subscribe { colors -> simpleStringAdapter.setStrings(colors) }
    }

    override fun onStop() {
        super.onStop()
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}