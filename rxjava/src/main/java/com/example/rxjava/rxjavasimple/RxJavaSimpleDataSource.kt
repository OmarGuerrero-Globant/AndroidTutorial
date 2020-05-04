package com.example.rxjava.rxjavasimple

import android.os.SystemClock
import io.reactivex.Observable

class RxJavaSimpleDataSource {

    fun getData() : Observable<Int> {
        return Observable.create {
            SystemClock.sleep(1000)
            it.onNext(5)
            it.onNext(6)
            SystemClock.sleep(1000)
            it.onNext(7)
            it.onComplete()
        }
    }

}