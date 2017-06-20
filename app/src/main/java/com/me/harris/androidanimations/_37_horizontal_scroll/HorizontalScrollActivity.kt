package com.me.harris.androidanimations._37_horizontal_scroll

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.me.harris.androidanimations.BaseAppCompatActivity
import com.me.harris.androidanimations.R

/**
 * Courtesy of https://github.com/adityagohad/HorizontalPicker
 */
class HorizontalScrollActivity : BaseAppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_picker)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

    }




}