package com.me.harris.androidanimations._36_fun_kt

import android.os.Bundle
import android.widget.ImageView
import com.me.harris.androidanimations.BaseAppCompatActivity
import com.me.harris.androidanimations.R
import java.net.ServerSocket
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by Harris on 2017/5/23.
 */
class KActivity : BaseAppCompatActivity() {

    var mIvBeauty : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kt_main_activity)
        mIvBeauty = findViewById(R.id.iv_beauty)



    }

    fun calTime() {
        val startTime = System.currentTimeMillis()
        print("HelloWorld")
        val helloTook = System.currentTimeMillis() - startTime
        print("Saying Hello took me ${helloTook}ms")
    }

    fun callTime() {
        val mime = measureTimeMillis { print("hahha") }
        print("Hello Took ${mime} ms")
    }


    fun joint(sep: String, strings: List<String>): String {
        require(sep.length < 2) { "go eat shit!" }
        return "sss"
    }

    fun runServer(serverSocket: ServerSocket) {
        print("")
    }

    // extension functions
    fun Date.isTuesDay(): Boolean {
        return false

    }
}