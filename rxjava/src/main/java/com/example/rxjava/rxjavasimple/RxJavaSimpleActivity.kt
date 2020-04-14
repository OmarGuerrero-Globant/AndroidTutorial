package com.example.rxjava.rxjavasimple

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.common.utils.toast
import kotlinx.android.synthetic.main.activity_rxjavasimple.*

class RxJavaSimpleActivity : AppCompatActivity() , RxJavaSimplePresenter.View{
    private val presenter = RxJavaSimplePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjavasimple)
        presenter.onCreate(this)
    }

    fun onClick(view: View) {
        presenter.onOptionSelected(view.id)
    }

    override fun updateTheUserInterface(item: Int) {
        resultView.text = item.toString()
    }

    override fun displayMessage(message: String) {
        toast(message)
    }

    override fun enable(boolean: Boolean) {
        button.isEnabled = boolean
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