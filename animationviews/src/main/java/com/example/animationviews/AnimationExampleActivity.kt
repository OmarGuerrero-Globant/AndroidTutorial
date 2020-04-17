package com.example.animationviews

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animation_example.*


class AnimationExampleActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_example)
    }

    fun startAnimation(view: View) {
        var dest = 0f
        when (view.id) {
            R.id.Button01 -> {
                dest = 360f
                if (imageView1.rotation == 360F) {
                    println(imageView1.alpha)
                    dest = 0f
                }
                val animation1 = ObjectAnimator.ofFloat(
                    imageView1,
                    "rotation", dest
                ).apply {
                    duration = 2000
                    start()
                }
            }
            R.id.Button02 -> {
                val paint = Paint()

                val measureTextCenter: Float = paint.measureText(textView1.text.toString())
                dest = 0 - measureTextCenter
                if (textView1.x < 0) {
                    dest = 0f
                }
                val animation2 = ObjectAnimator.ofFloat(
                    textView1,
                    "x", dest
                ).apply {
                    duration = 2000
                    start()
                }
            }
            R.id.Button03 -> {
                dest = 1f
                if (imageView1.alpha > 0) {
                    dest = 0f
                }
                val animation3 = ObjectAnimator.ofFloat(imageView1, "alpha", dest).apply {
                    duration = 2000
                    start()
                }
            }
            R.id.Button04 -> {
                val fadeOut = ObjectAnimator.ofFloat(
                    imageView1, "alpha",
                    0f
                ).apply {
                    duration = 2000
                }
                val mover = ObjectAnimator.ofFloat(
                    imageView1,
                    "translationX", -500f, 0f
                ).apply {
                    duration = 2000
                }
                val fadeIn = ObjectAnimator.ofFloat(
                    imageView1, "alpha",
                    0f, 1f
                ).apply {
                    duration = 2000
                }
                val animatorSet = AnimatorSet().apply {
                    play(mover).with(fadeIn).after(fadeOut)
                    start()
                }
            }
            else -> {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, HitActivity::class.java)
        startActivity(intent)
        return true
    }
}