package com.me.harris.androidanimations._36_fun_kt

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.me.harris.androidanimations.BaseAppCompatActivity
import com.me.harris.androidanimations.R
import java.net.ServerSocket
import java.util.*
import kotlin.system.measureTimeMillis

/**
 * Created by Harris on 2017/5/23.
 */
class KActivity : BaseAppCompatActivity() {

    var mIvBeauty: View? = null

    private val deleteByFirstName by lazy {
        // lazy load
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kt_main_activity)
        mIvBeauty = findViewById(R.id.iv_beauty)


    }


    fun iteratingViewGroups(views: ViewGroup) {
        // 1. verbose mode
        for (index in 0 until views.childCount) {
            val view = views.getChildAt(index)
        }

        // 2. more simplified version
        views.forEach { view -> print("height is " + view.height) } // a much more simplify version

        // 3. if wanna index , here they are
        views.forEachIndexed { i, view -> print("child index at $i height is ${view.height}") }
    }

    inline fun ViewGroup.forEach(action: (View) -> Unit) {
        for (index in 0 until childCount) {
            action(getChildAt(`index`))
        }
    }

    // inline keywords prevent anonymous inner class
    inline fun ViewGroup.forEachIndexed(action: (Int, View) -> Unit) {
        for (index in 0 until childCount) {
            action(index, getChildAt(`index`))
        }
    }


    fun tryOperatorFunction(v: ViewGroup) {
        val views = v

        val first = views[0] // retrieve the first child of a view

        // add or remove a view like treating an collection
        views += first
        views -= first

        // you can also use
        views.plusAssign(first)
        views.minusAssign(first)

        if (first in views) doSomething()

        // extension properities
        print(views.size)

        // pass some function to trace and expect some result
      val  result = trace("foo"){
          doSomething()
      }






    }






    fun doSomething() {
        println("do someThing")
    }

    // this allowed you to manipulate viewGroups like collections
    operator fun ViewGroup.get(index: Int): View = getChildAt(index)

    operator fun ViewGroup.minusAssign(child: View) = removeView(child)

    operator fun ViewGroup.plusAssign(child: View) = addView(child)
    operator fun ViewGroup.contains(child: View) = indexOfChild(child) != -1

    val ViewGroup.size: Int
        get() = childCount


    inline fun SQLiteDatabase.transaction(body: () -> Unit) {
        beginTransaction()
        try {
            body()
            setTransactionSuccessful()
        }finally {
            endTransaction()
        }
    }


    inline fun SQLiteDatabase.transaction(body: (SQLiteDatabase) -> Unit) {
        beginTransaction()
        try {
            body(this)
            setTransactionSuccessful()
        }finally {
            endTransaction()
        }
    }

    fun createDummyList() {
        val list = listOf(1, 2, 3, 4, 5)
        var ViewList = arrayListOf<String>()
        ViewList.add("")
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