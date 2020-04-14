package com.example.rxjava.main

import com.example.rxjava.R

class MainPresenter {

    interface View {
        fun onSelectedActivity(activity : String)
    }

    private var view: View ? = null

    fun onCreate(view : View){
        this.view = view
    }

    fun onDestroy(){
        this.view = null
    }

    fun onOptionsItemSelected(id: Int){
        val activity = when (id) {
            R.id.first -> "RxJavaSimpleActivity"
            R.id.second -> "ColorsActivity"
            R.id.third -> "BooksActivity"
            R.id.fourth -> "SchedulerActivity"
            else -> "MainActivity"
        }
        view?.onSelectedActivity(activity)
    }
}