package com.me.harris.textviewtest.paser

import com.squareup.moshi.Moshi

class MoshiHolder private constructor(){

    companion object {
      val instance:MoshiHolder by lazy {
          MoshiHolder()
      }
    }

    val moshi :Moshi by lazy {
        Moshi .Builder().build()
    }
}