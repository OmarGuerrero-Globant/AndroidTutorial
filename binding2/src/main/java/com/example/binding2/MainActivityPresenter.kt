package com.example.binding2

import android.content.Context
import com.example.binding2.MainActivityContract.Presenter

class MainActivityPresenter(
    private val view: MainActivityContract.View,
    ctx: Context
) :
    Presenter {
    private val ctx: Context = ctx
    override fun onShowData(temperatureData: TemperatureData?) {
        view.showData(temperatureData)
    }

}