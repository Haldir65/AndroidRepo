package com.me.harris.gridsample

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SimpleDecoration : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (view is FrameLayout && view.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            val manager = parent.layoutManager
            val spanIndex = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
            outRect?.top = 5
            outRect?.bottom = 5
            when (spanIndex) {
                0 -> {
                    outRect.left = 10
                    outRect.right = 5
                }
                else -> {
                    outRect.left = 5

                    outRect.right = 10
                }
            }


        }

    }
}