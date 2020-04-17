package com.example.localservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


object Util {
    fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, TestJobService::class.java)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency(1 * 1000.toLong())
        builder.setOverrideDeadline(3 * 1000.toLong())
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        //builder.setRequiresDeviceIdle(true)
        //builder.setRequiresCharging(false)
        val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
        jobScheduler.schedule(builder.build())
    }
}