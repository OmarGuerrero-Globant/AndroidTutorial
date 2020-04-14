package com.example.rxjava.colors

class ColorsContract {

    interface View{
        fun updateStringAdapter(colors : List<String>)
    }

    interface Presenter{
        fun onCreate(view : ColorsContract.View)
        fun onDestroy()
        fun initDisposable()
        fun cancelDisposable()
    }
}