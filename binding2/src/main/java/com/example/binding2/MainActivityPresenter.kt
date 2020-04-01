package com.example.binding2

import android.content.Context
import android.content.Intent
import com.example.binding2.MainActivityContract.Presenter

class MainActivityPresenter(
    private val view: MainActivityContract.View,
    private val ctx: Context
) :
    Presenter {
    override fun onShowData(temperatureData: TemperatureData?) {
        view.showData(temperatureData)
    }

    override fun showList() {
        val i = Intent(ctx, SecondActivity::class.java)
        ctx.startActivity(Intent.createChooser(i, "Share with").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}