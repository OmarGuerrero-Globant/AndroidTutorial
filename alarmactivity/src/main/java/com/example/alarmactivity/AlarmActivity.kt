package com.example.alarmactivity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AlarmActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startAlert(view: View?) {
        val text = findViewById<View>(R.id.time) as EditText
        val i = text.text.toString().toInt()
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this.applicationContext, 234324243, intent, 0
        )
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + i * 1000] = pendingIntent
        Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG).show()
    }
}