package com.me.harris.androidanimations._36_fun_kt

/**
 * Created by Harris on 2017/5/24.
 */

open class Payloads{
    data class Favorite(val favorited: Boolean) : Payloads ()

    data class Retweet(val retweet: Boolean,val name:String) : Payloads()


}