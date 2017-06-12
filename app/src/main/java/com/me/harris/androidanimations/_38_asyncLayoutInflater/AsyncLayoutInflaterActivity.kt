package com.me.harris.androidanimations._38_asyncLayoutInflater

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.view.AsyncLayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.harris.androidanimations.BaseAppCompatActivity
import com.me.harris.androidanimations.R
import com.me.harris.androidanimations.utils.LogUtil
import com.me.harris.androidanimations.utils.ToastUtils

/**
 * Created by Harris on 2017/6/12.
 * 1. compared with traditional way of inflating views, AsyncLayoutInflater free the main thread, leaving a considerable space for imagination.
 * eg , send request for data ,either network or local storage
 *
 * 2. inflating the view is far away from displaying the UI , i put a lot image in xml, so the screen will not transform even when view is created
 *
 * 3. remember creating the view is simple calling the constructor on the view , so onMeasure, onLayout,onDraw still happens on the ui thread
 *
 * 4. in general , it's good , but not a silver bullet
 *
 */
class AsyncLayoutInflaterActivity : BaseAppCompatActivity() {

    var time: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this is supposed to be a super heavy layout , with multiple layer of nested children
        asyncInflate(R.layout.activity_super_heavy) //  onInflateFinished]]Finish took 147 milliseconds , the second time took  41 milliseconds
//        the_way_traditional_inflate_would_do(R.layout.activity_super_heavy)// Finish took 148 milliseconds, the second time took 16 milliseconds

    }

    fun asyncInflate(@LayoutRes id: Int) {
        time = System.currentTimeMillis()
        AsyncLayoutInflater(this).inflate(id, null,
                { view: View, i: Int, viewGroup: ViewGroup? ->
                    setContentView(view)
                    val elapse = System.currentTimeMillis() - time
                    ToastUtils.showTextShort(this, "Finish took " + elapse + " milliseconds")
                    LogUtil.d("Finish took " + elapse + " milliseconds")

                })
    }

    fun the_way_traditional_inflate_would_do(@LayoutRes id: Int) {
        time = System.currentTimeMillis()
        setContentView(id)
        val elapse = System.currentTimeMillis() - time
        ToastUtils.showTextShort(this, "Finish took " + elapse + " milliseconds")
        LogUtil.d("Finish took " + elapse + " milliseconds")
    }




}


