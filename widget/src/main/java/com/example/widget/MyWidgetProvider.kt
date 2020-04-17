package com.example.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log

class MyWidgetProvider : AppWidgetProvider() {

    companion object {
        private const val LOG = "android.example.widget"
    }

    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.w(LOG, "onUpdate method called")
        val thisWidget = ComponentName(
            context,
            MyWidgetProvider::class.java
        )
        val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)

        val intent = Intent(context.applicationContext, UpdateWidgetService::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds)
        context.startService(intent)
    }
}