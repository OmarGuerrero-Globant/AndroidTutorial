package com.example.rxjava.scheduler

import com.example.rxjava.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

class SchedulerPresenter(private val dataSource: DataSource): SchedulerContract.Presenter {
    private val subscription: Disposable? = null
    private var view: SchedulerContract.View? = null
    private var callable: Callable<String> = Callable<String> { dataSource.getData()}

    override fun onCreate(view : SchedulerContract.View){
        this.view = view
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun onButtonSelected(viewId : Int){
        when(viewId){
            R.id.scheduleLongRunningOperation -> Observable.fromCallable(callable).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                    view?.updateProgressBar(android.view.View.VISIBLE)
                    view?.enableButton(false)
                    view?.setMessage("Progressbar set visible")
                }.subscribe(getDisposableObserver())
        }
    }

    private fun getDisposableObserver() : DisposableObserver<String> {
        return object : DisposableObserver<String>(){
            override fun onComplete() {
                view?.setMessage("OnComplete")
                view?.updateProgressBar(android.view.View.INVISIBLE)
                view?.enableButton(true)
                view?.setMessage("Hidding Progressbar")
            }

            override fun onError(e: Throwable) {
                view?.setMessage("OnError")
                view?.updateProgressBar(android.view.View.INVISIBLE)
                view?.enableButton(true)
                view?.setMessage("Hidding Progressbar")
            }

            override fun onNext(message: String) {
                view?.setMessage("nonNext $message")
            }
        }
    }

    override fun cancelDisposable(){
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        }
    }
}