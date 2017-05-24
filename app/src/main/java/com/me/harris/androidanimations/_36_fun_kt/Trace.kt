package com.me.harris.androidanimations._36_fun_kt

import android.os.Trace

/**
 * Created by Harris on 2017/5/24.
 */

inline fun <T> trace(sectionName: String, body: () -> T) :T {
    Trace.beginSection(sectionName)
    try {
       return body()
    }finally {
        Trace.endSection()
    }
}