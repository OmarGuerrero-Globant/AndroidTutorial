package com.example.rxjava.scheduler

import android.os.SystemClock

class SchedulerModel {
    companion object{
        fun doSomethingLong(): String {
            SystemClock.sleep(1000)
            return "Hello"
        }
    }
}