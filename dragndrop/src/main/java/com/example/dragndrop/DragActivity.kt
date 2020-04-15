package com.example.dragndrop

import android.content.ClipData
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drag.*

class DragActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag)
        myimage1.setOnTouchListener(MyTouchListener())
        myimage2.setOnTouchListener(MyTouchListener())
        myimage3.setOnTouchListener(MyTouchListener())
        myimage4.setOnTouchListener(MyTouchListener())
        topleft.setOnDragListener(MyDragListener())
        topright.setOnDragListener(MyDragListener())
        bottomleft.setOnDragListener(MyDragListener())
        bottomright.setOnDragListener(MyDragListener())

    }

    inner class MyTouchListener : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(view)
                with(view) {
                    startDrag(data, shadowBuilder, this, 0)
                    visibility = View.INVISIBLE
                }
                true
            } else {
                false
            }
        }
    }

    inner class MyDragListener : View.OnDragListener {
        private var enterShape : Drawable? = resources.getDrawable(R.drawable.shape_droptarget)
        private var normalShape: Drawable? = resources.getDrawable(R.drawable.shape)
        override fun onDrag(v: View, event: DragEvent): Boolean {
            val action = event.action
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> { }
                DragEvent.ACTION_DRAG_ENTERED -> v.setBackgroundDrawable(enterShape)
                DragEvent.ACTION_DRAG_EXITED -> v.setBackgroundDrawable(normalShape)
                DragEvent.ACTION_DROP -> {
                    val view = event.localState as View
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = v as LinearLayout
                    container.addView(view)
                    view.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_ENDED -> v.setBackgroundDrawable(normalShape)
                else -> { }
            }
            return true
        }
    }
}