package com.example.rxjava.rxjavasimple

import com.example.rxjava.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RxJavaSimplePresenter(private val dataSource: RxJavaSimpleDataSource) : RxJavaSimpleContract.Presenter {
    var disposable: CompositeDisposable = CompositeDisposable()
    var value = 0

    private var view: RxJavaSimpleContract.View? = null

    override fun onCreate(view : RxJavaSimpleContract.View){
        this.view = view
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun onOptionSelected(viewId : Int){
        when(viewId){
            R.id.button -> observeServerDownload()
            R.id.toastbutton -> view?.displayMessage("Still active ${value++}")
        }
    }

    private fun observeServerDownload(){
        view?.enable(false)
        val subscribe: Disposable =
            dataSource.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeWith(object : DisposableObserver<Int>(){
                    override fun onComplete() {
                        view?.displayMessage("Has completed")
                        view?.enable(true)
                    }

                    override fun onNext(item: Int) {
                        view?.updateTheUserInterface(item)
                    }

                    override fun onError(e: Throwable?) {
                        view?.displayMessage("Has ocurred an error : $e")
                        view?.enable(true)
                    }
                })
        disposable.add(subscribe)
    }

    override fun cancelDisposable(){
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }
}