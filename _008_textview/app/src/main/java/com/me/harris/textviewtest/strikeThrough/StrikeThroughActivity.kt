package com.me.harris.textviewtest.strikeThrough

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.me.harris.textviewtest.R
import kotlinx.android.synthetic.main.activity_strike_through.*
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan


class StrikeThroughActivity:AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strike_through)
        val sp = SpannableString("原价399元")
//        sp.setSpan(StrikethroughSpan(),5,sp.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sp.setSpan(RelativeSizeSpan(1.2f),2,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sp.setSpan(ForegroundColorSpan(Color.BLACK),2,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        tv_strike.text = sp
        tv_second.text = sp

    }
}