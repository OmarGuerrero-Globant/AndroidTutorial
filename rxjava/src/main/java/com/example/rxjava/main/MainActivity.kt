package com.example.rxjava.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R

class MainActivity : AppCompatActivity() , MainPresenter.View {
    private val presenter =  MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreate(this)
    }

    fun onClick(view: View) {
        presenter.onOptionsItemSelected(view.id)
    }

    override fun onSelectedActivity(idActivity: Class<out AppCompatActivity>) {
        startActivity(Intent(this, idActivity))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
