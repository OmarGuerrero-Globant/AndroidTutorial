package com.example.rxjava.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.main.MainContract
import com.example.rxjava.main.MainPresenter

class MainActivity : AppCompatActivity() ,
    MainContract.View {
    private val presenter = MainPresenter()

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
