package com.example.domain.usecases

import com.example.domain.executors.PostExecutionThread
import com.example.domain.executors.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class GroupUseCase<OUTPUT : Any>(private val threadExecutor: ThreadExecutor,
                                          private val postExecutionThread: PostExecutionThread){

    abstract fun buildGroupUseCase() : Single<OUTPUT>

    fun execute() : Single<OUTPUT> {
        return this.buildGroupUseCase().subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }
}