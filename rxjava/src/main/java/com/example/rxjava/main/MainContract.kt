package com.example.rxjava.main

import androidx.appcompat.app.AppCompatActivity

class MainContract {

    interface View {
        fun onSelectedActivity(idActivity : Class<out AppCompatActivity>)
    }

    interface Presenter {
        fun onCreate(view : View)
        fun onDestroy()
        fun onOptionsItemSelected(id: Int)
    }

}