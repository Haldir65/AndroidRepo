package com.me.harris.textviewtest.utils

import android.content.Context
import android.content.res.Resources



fun Context.screenWitdth():Int{
   return Resources.getSystem().displayMetrics.widthPixels
}

fun Context.screenHeight():Int{
   return Resources.getSystem().displayMetrics.heightPixels
}