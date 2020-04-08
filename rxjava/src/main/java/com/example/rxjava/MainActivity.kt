package com.example.rxjava

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.first -> i = Intent(this, RxJavaSimpleActivity::class.java)
            R.id.second -> i = Intent(this, ColorsActivity::class.java)
            R.id.third -> i = Intent(this, BooksActivity::class.java)
        }
        startActivity(i)
    }
}
