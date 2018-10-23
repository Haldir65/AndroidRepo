package com.me.harris.androidanimations._37_horizontal_scroll

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by Harris on 2017/6/12.
 */
class PickerLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) : LinearLayoutManager(context, orientation, reverseLayout) {
    private val scaleDownBy = 0.66f
    private val scaleDownDistance = 0.9f
    private val changeAlpha = true

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            val mid = width / 2.0f
            val unitScaleDownDist = scaleDownDistance * mid
            for (i in 0..childCount - 1) {
                val child = getChildAt(i)
                val childMid = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f
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
            if (scrollListener != null) {
                var selected = 0
                var lastHeight = 0f
                for (i in 0 until childCount) {
                    if (lastHeight < getChildAt(i)!!.scaleY) {
                        lastHeight = getChildAt(i)!!.scaleY
                        selected = i
                    }
                }
                (scrollListener as onScrollStopListener).selectedView(getChildAt(selected)!!)
            }
        }
    }

    private var scrollListener: onScrollStopListener? = null


    interface onScrollStopListener {
        fun selectedView(view: View)
    }




}