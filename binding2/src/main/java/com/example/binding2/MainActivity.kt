package com.example.binding2

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.binding2.databinding.ActivityMainBinding


class MainActivity : Activity(), MainActivityContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val mainActivityPresenter =
            MainActivityPresenter(this, applicationContext)
        val temperatureData = TemperatureData("Hamburg", "10")
        binding.temp = temperatureData
        binding.presenter = mainActivityPresenter
    }

    override fun showData(temperatureData: TemperatureData?) {
        val celsius = temperatureData!!.getCelsius()
        Toast.makeText(this, celsius, Toast.LENGTH_SHORT).show()
    }
}