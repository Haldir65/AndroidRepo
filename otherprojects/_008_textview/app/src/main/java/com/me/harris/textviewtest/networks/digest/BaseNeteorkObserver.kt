package com.me.harris.textviewtest.networks.digest

import io.reactivex.observers.DisposableObserver

 open class BaseNeteorkObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
        System.out.println("done")
    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        System.out.println(e.message)
    }

}