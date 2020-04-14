package com.example.rxjava.main

import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.books.BooksActivity
import com.example.rxjava.colors.ColorsActivity
import com.example.rxjava.rxjavasimple.RxJavaSimpleActivity
import com.example.rxjava.scheduler.SchedulerActivity

class MainPresenter {

    interface View {
        fun onSelectedActivity(idActivity : Class<out AppCompatActivity>)
    }

    private var view: View ? = null

    fun onCreate(view : View){
        this.view = view
    }

    fun onDestroy(){
        this.view = null
    }

    fun onOptionsItemSelected(id: Int){
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