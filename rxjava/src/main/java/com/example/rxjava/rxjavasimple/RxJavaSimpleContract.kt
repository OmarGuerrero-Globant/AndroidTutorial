package com.example.rxjava.rxjavasimple

class RxJavaSimpleContract {

    interface View {
        fun enable(boolean: Boolean)
        fun updateTheUserInterface(item : Int)
        fun displayMessage(message : String)
    }

    interface Presenter {
        fun onCreate(view : RxJavaSimpleContract.View)
        fun onDestroy()
        fun onOptionSelected(viewId : Int)
        fun cancelDisposable()
    }
}