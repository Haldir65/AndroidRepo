package com.me.harris.textviewtest.strikeThrough

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

class ThroughTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr){

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}