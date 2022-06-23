package com.me.harris.textviewtest.round_img

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableString
import android.widget.TextView
import com.me.harris.textviewtest.R
import kotlinx.android.synthetic.main.activity_rounded_bg.*

class RoundBgActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rounded_bg)
        val sp = SpannableString("99+").apply {
            setSpan(RoundBackgroundColorSpan(this@RoundBgActivity),0,this.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv_1.setText(sp,TextView.BufferType.SPANNABLE)
    }
}