package com.example.binding2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SecondActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recyclerView = findViewById<View>(R.id.my_recycler_view) as RecyclerView

        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        val items: List<TemperatureData> =
            mutableListOf(TemperatureData("Hamburg", "5"), TemperatureData("Berlin", "6"))

        mAdapter = MyAdapter(items)
        recyclerView!!.adapter = mAdapter
    }
}