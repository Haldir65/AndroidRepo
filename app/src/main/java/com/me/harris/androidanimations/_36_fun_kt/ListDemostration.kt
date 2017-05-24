package com.me.harris.androidanimations._36_fun_kt

/**
 * Created by Harris on 2017/5/24.
 */

sealed class ListRound{

    fun iterateEach(l: List<String>) {
        l.forEachIndexed { index, s ->
            if (index == l.size-3) {
                l[index] = ""
            } else {
            } }

    }



}

private operator fun <E> List<E>.set(index: Int, value: E) {
    this[index] = value
}
