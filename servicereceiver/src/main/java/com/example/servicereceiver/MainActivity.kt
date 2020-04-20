package com.example.servicereceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val bundle = intent.extras
            if (bundle != null) {
                val string = bundle.getString(DownloadService.FILEPATH)
                val resultCode = bundle.getInt(DownloadService.RESULT)
                if (resultCode == RESULT_OK) {
                    Toast.makeText(
                        this@MainActivity,
                        "Download complete. Download URI: $string",
                        Toast.LENGTH_LONG
                    ).show()
                    status.text = "Download done"
                } else {
                    Toast.makeText(
                        this@MainActivity, "Download failed",
                        Toast.LENGTH_LONG
                    ).show()
                    status.text = "Download failed"
                }
            }
        }
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiver, IntentFilter(
                DownloadService.NOTIFICATION
            )
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    fun onClick(view: View?) {
        val intent = Intent(this, DownloadService::class.java).apply {
            putExtra(DownloadService.FILENAME, "index.html")
            putExtra(DownloadService.URL, "https://www.vogella.com/index.html")
            startService(this)
        }
        status.text = "Service started"
    }
}