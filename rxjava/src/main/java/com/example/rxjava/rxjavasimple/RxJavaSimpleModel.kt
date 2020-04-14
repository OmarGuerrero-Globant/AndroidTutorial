package com.example.rxjava.rxjavasimple

import android.os.SystemClock
import io.reactivex.rxjava3.core.Observable

class RxJavaSimpleModel {
    companion object{
        val serverDownloadObservable: Observable<Int> = Observable.create {
            SystemClock.sleep(1000)
            it.onNext(5)
            it.onNext(6)
            SystemClock.sleep(1000)
            it.onNext(7)
            it.onComplete()
        }
    }
}