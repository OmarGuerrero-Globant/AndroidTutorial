package com.example.localservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.localservice.Util.scheduleJob


class MyStartServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        scheduleJob(context)
    }
}