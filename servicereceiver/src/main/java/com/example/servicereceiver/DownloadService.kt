package com.example.servicereceiver

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Environment
import java.io.*
import java.net.URL

class DownloadService : IntentService("DownloadService") {
    private var result = Activity.RESULT_CANCELED

    companion object {
        const val URL = "urlpath"
        const val FILENAME = "filename"
        const val FILEPATH = "filepath"
        const val RESULT = "result"
        const val NOTIFICATION = "com.vogella.android.service.receiver"
    }

    override fun onHandleIntent(intent: Intent?) {
        val urlPath = intent?.getStringExtra(URL)
        val fileName = intent?.getStringExtra(FILENAME)
        val output = File(Environment.getExternalStorageDirectory(), fileName!!)
        if (output.exists()) {
            output.delete()
        }
        var stream: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            val url: URL = URL(urlPath)
            stream = url.openConnection().getInputStream()
            val reader = InputStreamReader(stream)
            fos = FileOutputStream(output.path)
            var next = -1
            while (reader.read().also { next = it } != -1) {
                fos.write(next)
            }
            result = Activity.RESULT_OK
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        publishResults(output.absolutePath, result)
    }

    private fun publishResults(outputPath: String, result: Int) {
        val intent = Intent(NOTIFICATION).apply {
            putExtra(FILEPATH, outputPath)
            putExtra(RESULT, result)
            sendBroadcast(this)
        }
    }
}