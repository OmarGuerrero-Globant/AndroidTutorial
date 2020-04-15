package com.example.drawables

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resource: InputStream = resources.openRawResource(R.raw.droid)
        val bitmap = BitmapFactory.decodeStream(resource)
        image.background = MyRoundCornerDrawable(bitmap)
    }
}