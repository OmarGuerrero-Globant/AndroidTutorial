package com.example.rxjava.colors

import android.content.Context

class DataSource {

    fun getColorList() : List<String> {
            val colors: ArrayList<String> = ArrayList()
            with(colors) {
                add("red")
                add("green")
                add("blue")
                add("pink")
                add("brown")
            }
            return colors
    }
}