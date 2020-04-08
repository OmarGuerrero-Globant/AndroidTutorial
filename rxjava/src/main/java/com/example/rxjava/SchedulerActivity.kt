package com.example.rxjava

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable.fromCallable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_scheduler.*
import java.util.concurrent.Callable


class SchedulerActivity() : AppCompatActivity() {
    private val subscription: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
    }

    private fun createObservable() {}

    override fun onDestroy() {
        super.onDestroy()
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        }
    }

    private fun configureLayout() {
        setContentView(R.layout.activity_scheduler)
        scheduleLongRunningOperation.setOnClickListener {
            fromCallable(callable).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                    progressBar.visibility = View.VISIBLE
                    scheduleLongRunningOperation.isEnabled = false
                    messagearea.text =
                        "${messagearea.text}\nProgressbar set visible"
                }.subscribe(getDisposableObserver())
        }
    }

    private var callable: Callable<String> = Callable<String> { doSomethingLong() }

    private fun doSomethingLong(): String {
        SystemClock.sleep(1000)
        return "Hello"
    }

    private fun getDisposableObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>(){
            override fun onComplete() {
                messagearea.text = "${messagearea.text}\nOnComplete"
                progressBar.visibility = View.INVISIBLE
                scheduleLongRunningOperation.isEnabled = true
                messagearea.text = "${messagearea.text}\nHidding Progressbar"
            }

            override fun onError(e: Throwable) {
                messagearea.text = "${messagearea.text}\nOnError"
                progressBar.visibility = View.INVISIBLE
                scheduleLongRunningOperation.isEnabled = true
                messagearea.text = "${messagearea.text}\nHidding Progressbar"
            }

            override fun onNext(message: String) {
                messagearea.text = "${messagearea.text}\nonNext $message"
            }
        }
    }
}