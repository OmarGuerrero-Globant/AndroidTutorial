package com.example.rxjava.colors

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

class ColorsPresenter {
    private lateinit var disposable: Disposable

    companion object {
        private fun getColorList() : List<String> {
            val colors: ArrayList<String> = ArrayList()
            with(colors) {
                add("red")
                add("green")
                add("blue")
                add("pink")
                add("brown")
            }
            return colors
        }
    }

    interface View{
        fun updateStringAdapter(colors : List<String>)
    }

    private var view: View? = null

    fun onCreate(view : View){
        this.view = view
    }

    fun onDestroy(){
        this.view = null
    }

    fun initDisposable(){
        val listObservable: Observable<List<String>> =
            Observable.just(getColorList())
        disposable =
            listObservable.subscribe { colors -> view?.updateStringAdapter(colors) }
    }

    fun cancelDisposable(){
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

}