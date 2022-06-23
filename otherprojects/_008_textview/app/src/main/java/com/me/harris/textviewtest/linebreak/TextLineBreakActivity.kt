package com.me.harris.textviewtest.linebreak

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import com.me.harris.textviewtest.R
import kotlinx.android.synthetic.main.activity_line_break.*

class TextLineBreakActivity :AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_break)
//        tv_second.text = ""

        tv_first.text = Html.fromHtml("This is <font color='red'>simple</font>.")
//        tv_second.text = Html.fromHtml("This is <font color='red'>simple</font>.")
//        tv_second.text = "This is <font color='red'>simple</font>."
        tv_second.text = Html.fromHtml("<html><body>Sorry, <span style=\\\"background: red;\\\">Madonna</span> gave no results</body></html>")

    }
}