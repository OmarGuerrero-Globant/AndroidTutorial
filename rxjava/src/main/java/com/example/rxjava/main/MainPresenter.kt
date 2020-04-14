package com.example.rxjava.main

import com.example.rxjava.R
import com.example.rxjava.books.BooksActivity
import com.example.rxjava.colors.ColorsActivity
import com.example.rxjava.rxjavasimple.RxJavaSimpleActivity
import com.example.rxjava.scheduler.SchedulerActivity

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun onCreate(view : MainContract.View){
        this.view = view
    }

    override fun onDestroy(){
        this.view = null
    }

    override fun onOptionsItemSelected(id: Int){
        val idActivity = when (id) {
            R.id.first -> RxJavaSimpleActivity::class.java
            R.id.second -> ColorsActivity::class.java
            R.id.third ->  BooksActivity::class.java
            R.id.fourth -> SchedulerActivity::class.java
            else ->  MainActivity::class.java
        }
        view?.onSelectedActivity(idActivity)
    }
}