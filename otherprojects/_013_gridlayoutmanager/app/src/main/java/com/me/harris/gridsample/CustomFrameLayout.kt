package com.me.harris.gridsample

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout

class CustomFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {
    init {
//        LayoutInflater.from(context)
//            .inflate(R.layout.view_custom_component, this, true)

//        orientation = VERTICAL
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val offset = 0
        super.onLayout(changed, left+offset, top, right+2*offset, bottom)
        Log.e("===","layout left = ${left} right= ${right} top = ${top} bottom = ${bottom}")
    }


}