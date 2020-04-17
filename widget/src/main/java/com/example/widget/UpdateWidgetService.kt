package com.example.widget

import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import java.util.*


class UpdateWidgetService : Service() {

    override fun onStart(intent: Intent, startId: Int) {
        val appWidgetManager = AppWidgetManager.getInstance(this.applicationContext)
        val allWidgetIds = intent
            .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)

        for (widgetId in allWidgetIds) {
            val number: Int = Random().nextInt(100)
            val remoteViews = RemoteViews(this.applicationContext.packageName, R.layout.widget_layout)
            Log.w("WidgetExample", number.toString())

            remoteViews.setTextViewText(R.id.update, "Random: $number")

            val clickIntent = Intent(this.applicationContext, MyWidgetProvider::class.java)
            clickIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            clickIntent.putExtra(
                AppWidgetManager.EXTRA_APPWIDGET_IDS,
                allWidgetIds
            )
            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent)
            appWidgetManager.updateAppWidget(widgetId, remoteViews)
        }
        stopSelf()
        super.onStart(intent, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val LOG = "android.example.widget"
    }
}