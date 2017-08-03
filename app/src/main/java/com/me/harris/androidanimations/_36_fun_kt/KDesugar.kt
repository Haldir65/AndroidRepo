package com.me.harris.androidanimations._36_fun_kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Harris on 2017/5/23.
 */
class KDesugar :Runnable {

    override fun run() {
        if (something()){
            print("demo of explosive placeHolders")
        }else{
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        }
    }

    private fun something(): Boolean {
        return true
    }

    class Maac : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // iterating iterabale with index
            val quoteParts = " YOU JUST TALKED TO MUCH !".split(" ")
            for ((index, value) in quoteParts.withIndex()) {
                print("reading index $index: $value ")
            }
        }
    }

}