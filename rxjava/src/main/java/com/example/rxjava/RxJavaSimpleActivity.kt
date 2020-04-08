package com.example.rxjava

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjavasimple.*

class RxJavaSimpleActivity : AppCompatActivity() {
    var disposable: CompositeDisposable = CompositeDisposable()
    var value = 0
    private val serverDownloadObservable: Observable<Int> = Observable.create {
        SystemClock.sleep(1000)
        it.onNext(5)
        it.onComplete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjavasimple)
        val view: View = findViewById(R.id.button)
        view.setOnClickListener { v ->
            v.isEnabled = false
            val subscribe: Disposable =
            serverDownloadObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe { integer ->
                    updateTheUserInterface(integer)
                    v.isEnabled = true
                }
            disposable.add(subscribe)
        }
    }

    private fun updateTheUserInterface(integer: Int) {
        resultView.text = integer.toString()
    }

    override fun onStop() {
        super.onStop()
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    fun onClick(view: View) {
        Toast.makeText(this, "Still active ${value++}", Toast.LENGTH_SHORT).show()
    }
}