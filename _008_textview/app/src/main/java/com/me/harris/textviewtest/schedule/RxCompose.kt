package com.me.harris.textviewtest.schedule

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxCompose {
    companion object {
        fun<T> io_mian():ObservableTransformer<T,T>{
            return ObservableTransformer {
                upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

            }

        }
    }



}