package com.example.listview

import android.app.ListActivity
import android.os.Bundle


class MainActivity : ListActivity() {
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        val values = arrayOf(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )
        val adapter = MySimpleArrayAdapter(this, values)
        listAdapter = adapter
    }
}