package com.example.binding2

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).placeholder(R.drawable.ic_listentry).into(view)
}

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView

        recyclerView.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val items: List<TemperatureData> =
            mutableListOf(TemperatureData("Hamburg", "5", "http://lorempixel.com/40/40/"),
                TemperatureData("Berlin", "6", "http://lorempixel.com/40/40/"))

        mAdapter = MyAdapter(items)
        recyclerView.adapter = mAdapter
    }
}