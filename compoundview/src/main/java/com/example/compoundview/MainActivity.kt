package com.example.compoundview

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    fun onClicked(view: View) {
        val text = if (view.id == R.id.view1) "Background" else "Foreground"
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        view1.setValueColor(resources.getColor(R.color.colorAccent))
        view2.setValueColor(resources.getColor(R.color.colorPrimary))
    }
}