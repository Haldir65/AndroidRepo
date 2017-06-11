package com.me.harris.androidanimations._37_horizontal_scroll

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *  https://github.com/adityagohad/HorizontalPicker
 */


class PickerLayoutManager : LinearLayoutManager {

    private var scaleDownBy = 0.66f
    private var scaleDownDistance = 0.9f
    private var changeAlpha = true

    private var onScrollStopListener: onScrollStopListener? = null

    fun PickerLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean): ??? {
        super(context, orientation, reverseLayout)
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            val mid = width / 2.0f
            val unitScaleDownDist = scaleDownDistance * mid
            for (i in 0..childCount - 1) {
                val child = getChildAt(i)
                val childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f
                val scale = 1.0f + -1 * scaleDownBy * Math.min(unitScaleDownDist, Math.abs(mid - childMid)) / unitScaleDownDist
                child.scaleX = scale
                child.scaleY = scale
                if (changeAlpha) {
                    child.alpha = scale
                }
            }
            return scrolled
        } else
            return 0
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == 0) {
            if (onScrollStopListener != null) {
                var selected = 0
                var lastHeight = 0f
                for (i in 0..childCount - 1) {
                    if (lastHeight < getChildAt(i).scaleY) {
                        lastHeight = getChildAt(i).scaleY
                        selected = i
                    }
                }
                onScrollStopListener!!.selectedView(getChildAt(selected))
            }
        }
    }

    fun getScaleDownBy(): Float {
        return scaleDownBy
    }

    fun setScaleDownBy(scaleDownBy: Float) {
        this.scaleDownBy = scaleDownBy
    }

    fun getScaleDownDistance(): Float {
        return scaleDownDistance
    }

    fun setScaleDownDistance(scaleDownDistance: Float) {
        this.scaleDownDistance = scaleDownDistance
    }

    fun isChangeAlpha(): Boolean {
        return changeAlpha
    }

    fun setChangeAlpha(changeAlpha: Boolean) {
        this.changeAlpha = changeAlpha
    }

    fun setOnScrollStopListener(onScrollStopListener: onScrollStopListener) {
        this.onScrollStopListener = onScrollStopListener
    }

    interface onScrollStopListener {
        fun selectedView(view: View)
    }
}


