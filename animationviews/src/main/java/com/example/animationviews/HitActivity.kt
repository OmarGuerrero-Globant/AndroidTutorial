package com.example.animationviews

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.target.*
import java.util.*


class HitActivity : AppCompatActivity() {
    private lateinit var animation1: ObjectAnimator
    private lateinit var animation2: ObjectAnimator
    private lateinit var randon: Random
    private var width = 0
    private var height = 0
    private lateinit var set: AnimatorSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.target)
        width = windowManager.defaultDisplay.width
        height = windowManager.defaultDisplay.height
        randon = Random()
        set = createAnimation().apply {
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    val nextX: Int = randon.nextInt(width)
                    val nextY: Int = randon.nextInt(height)
                    animation1 = ObjectAnimator.ofFloat(button1, "x", button1.x, nextX.toFloat()).apply {
                        duration = 1400
                    }
                    animation2 = ObjectAnimator.ofFloat(button1, "y", button1.y, nextY.toFloat()).apply {
                        duration = 1400
                    }
                    set.playTogether(animation1, animation2).apply { start() }
                }
            })}
    }

    fun onClick(view: View?) {
        val string: String = button1.text.toString()
        val hitTarget = Integer.valueOf(string) + 1
        button1.text = hitTarget.toString()
    }

    private fun createAnimation(): AnimatorSet {
        val nextX: Int = randon.nextInt(width)
        val nextY: Int = randon.nextInt(height)
        animation1 = ObjectAnimator.ofFloat(button1, "x", nextX.toFloat()).apply { duration = 1400 }
        animation2 = ObjectAnimator.ofFloat(button1, "y", nextY.toFloat()).apply { duration = 1400 }
        return AnimatorSet().apply { playTogether(animation1, animation2) }
    }
}