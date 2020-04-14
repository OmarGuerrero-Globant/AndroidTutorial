package com.example.rxjava.scheduler

import android.os.SystemClock

class DataSource {

    fun getData(): String {
            SystemClock.sleep(1000)
            return "Hello"
    }

}