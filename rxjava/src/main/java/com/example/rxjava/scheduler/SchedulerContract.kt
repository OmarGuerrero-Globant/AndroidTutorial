package com.example.rxjava.scheduler

class SchedulerContract {

    interface View {
        fun updateProgressBar(visibility : Int)
        fun enableButton(isEnabled: Boolean)
        fun setMessage(message: String)
    }

    interface Presenter {
        fun onCreate(view : View)
        fun onDestroy()
        fun onButtonSelected(viewId : Int)
        fun cancelDisposable()
    }
}