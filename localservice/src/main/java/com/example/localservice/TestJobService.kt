package com.example.localservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.example.localservice.Util.scheduleJob


class TestJobService : JobService() {
    override fun onStartJob(params: JobParameters): Boolean {
        val service = Intent(applicationContext, LocalWordService::class.java)
        applicationContext.startService(service)
        scheduleJob(applicationContext)
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean = true

    companion object {
        private const val TAG = "SyncService"
    }
}