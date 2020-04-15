package com.example.drawables

import android.graphics.*
import android.graphics.drawable.Drawable


class MyRoundCornerDrawable(bitmap: Bitmap?) : Drawable() {
    private val paint: Paint

    init {
        val shader: BitmapShader = BitmapShader(bitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint = Paint()
        paint.isAntiAlias = true
        paint.shader = shader
    }

    override fun draw(canvas: Canvas) {
        val height = bounds.height()
        val width = bounds.width()
        val rect = RectF(0.0f, 0.0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, 30F, 30F, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        paint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}