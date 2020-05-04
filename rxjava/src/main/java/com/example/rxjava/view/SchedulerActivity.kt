package com.example.rxjava.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.scheduler.DataSource
import com.example.rxjava.scheduler.SchedulerContract
import com.example.rxjava.scheduler.SchedulerPresenter
import kotlinx.android.synthetic.main.activity_scheduler.*

class SchedulerActivity : AppCompatActivity() ,
    SchedulerContract.View {
    private val dataSource = DataSource()
    private val presenter =
        SchedulerPresenter(dataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheduler)
        presenter.onCreate(this)
    }

    fun onClick(view: View){
        presenter.onButtonSelected(view.id)
    }

    override fun updateProgressBar(visibility: Int) {
        progressBar.visibility = visibility
    }

    override fun enableButton(isEnabled: Boolean) {
        scheduleLongRunningOperation.isEnabled = isEnabled
    }

    override fun setMessage(message: String) {
        val toDisplay  = "${messagearea.text}\n$message"
        messagearea.text = toDisplay
    }

    override fun onStop() {
        presenter.cancelDisposable()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}