package com.me.harris.textviewtest.round_img

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import com.me.harris.textviewtest.utils.Utils

class RoundBackgroundColorSpan(val context: Context) : ReplacementSpan() {

    private var mSize = 0f

    private val leftRightPadding by lazy {
        Utils.dip2px(context, 10F)
    }

    private val cornerRadius by lazy {
        Utils.dip2px(context, 15F).toFloat()
    }

    // 获取宽度
    override fun getSize(paint: Paint?, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        mSize = paint!!.measureText(text, start, end)
        return mSize.toInt() + leftRightPadding * 2
    }

    override fun draw(canvas: Canvas?, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint?) {
        paint?.run {
            val the_x = x + leftRightPadding
            val c_original = color
            //画圆形或者圆角背景

            text?.run {
                color = Color.RED
                style = Paint.Style.FILL
                isAntiAlias = true
                val metrics = fontMetrics
                val mstrokeWidth = strokeWidth
                if (length == 1) {
                    //画圆形
                    val textHeight = metrics.bottom -  metrics.top
                    val circleRadius = Math.max(textHeight/2,mSize/2)
                    canvas?.drawCircle(mSize / 2+leftRightPadding, textHeight / 2, circleRadius.toFloat(), paint) //画圆形
                } else {
                    if (length > 1) {
                        // 画实心圆角矩形
                        val oval = RectF(the_x + mstrokeWidth + 0.5f - leftRightPadding*0.5f, y + metrics.ascent, the_x + mSize + mstrokeWidth + 0.5f + leftRightPadding*0.5f, y + metrics.descent)
                        canvas?.drawRoundRect(oval, cornerRadius, cornerRadius, paint)
                    }
                }
                //画文字
                color = Color.WHITE
                style = Paint.Style.STROKE
                canvas?.drawText(text, start, end, the_x, y.toFloat(), paint)
                color = c_original
            }
        }

    }
}