package com.example.rxjava.colors

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

class ColorsPresenter(private val dataSource: DataSource) : ColorsContract.Presenter {
    private lateinit var disposable: Disposable
    private var view: ColorsContract.View? = null

    override fun onCreate(view : ColorsContract.View){
        this.view = view
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun initDisposable(){
        val listObservable: Observable<List<String>> =
            Observable.just(dataSource.getColorList())
        disposable =
            listObservable.subscribe { colors -> view?.updateStringAdapter(colors) }
    }

    override fun cancelDisposable(){
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

}