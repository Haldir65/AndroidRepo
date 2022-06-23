package com.me.harris.droidx.cronet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.me.harris.droidx.R
import kotlinx.android.synthetic.main.activity_cronet.*
import org.chromium.net.CronetEngine
import java.util.concurrent.Executors


// https://developer.android.com/guide/topics/connectivity/cronet/
class CronetActivity :AppCompatActivity(){
    // https://jsonplaceholder.typicode.com/posts

    // Network requests issued using the Cronet Library are asynchronous by default.

    // read() -> onReadCompleted() -> read() -> .... onSucceeded()/aka finished

    companion object {
        val JSON_URL = "https://jsonplaceholder.typicode.com/posts"
        val TAG = "CronetActivity"
    }

    val cronetEngine:CronetEngine by lazy {
        val myBuilder = CronetEngine.Builder(this)
         myBuilder.build()
    }

    val executor by lazy {
         Executors.newSingleThreadExecutor()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronet)

        btn_1.setOnClickListener {
            val callback = SimpleUrlRequestCallback(

            )
            val builder = cronetEngine.newUrlRequestBuilder(
                JSON_URL, callback, executor
            )
            // Measure the start time of the request so that
            // we can measure latency of the entire request cycle
            callback.start = System.nanoTime()
            // Start the request
            builder.build().start()
            Log.e(TAG,"STARTED")
        }
    }
}