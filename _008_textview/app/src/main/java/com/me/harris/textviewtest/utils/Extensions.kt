package com.me.harris.textviewtest.utils

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun Context.screenWitdth():Int{
   return Resources.getSystem().displayMetrics.widthPixels
}

fun Context.screenHeight():Int{
   return Resources.getSystem().displayMetrics.heightPixels
}

fun ViewGroup.inflate(layoutId:Int): View {
   return LayoutInflater.from(context).inflate(layoutId,this,false)
}

inline fun ViewGroup.forEach(action: (View) -> Unit) {
   for (index in 0 until childCount) {
      action(getChildAt(index))
   }
}
//val views = // ...
//  views.forEach {view ->
//           // TODO do something with view
//        }


inline fun ViewGroup.forEachIndexed(action: (Int, View) -> Unit) {
   for (index in 0 until childCount) {
      action(index, getChildAt(index))
   }
}

// views.forEachIndexed { index, view -> /* ... */ }1