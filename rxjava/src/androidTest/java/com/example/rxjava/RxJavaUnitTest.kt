package com.example.rxjava

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.observers.TestObserver
import junit.framework.Assert.assertTrue
import org.junit.Test


class RxJavaUnitTest {
    private lateinit var result : String

    @Test
    fun returnAValue() {
        result = ""
        val observer: Observable<String> = Observable.just("Hello")
        observer.subscribe { s -> result = s }
        assertTrue(result == "Hello")
    }

    @Test
    fun expectNPE() {
        val todoObservable: Observable<Todo> =
            Observable.create(ObservableOnSubscribe<Todo> { emitter ->
                try {
                    val todos = getTodos() ?: throw NullPointerException("todos was null")
                    for (todo in todos) {
                        emitter.onNext(todo)
                    }
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            })
        val testObserver = TestObserver<Any>()
        todoObservable.subscribeWith(testObserver)
        testObserver.assertError(NullPointerException::class.java)
    }

    private fun getTodos() : List<Todo>?{
        return null
    }

    inner class Todo
}