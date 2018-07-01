package com.me.harris.textviewtest.pattern

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject

class SubjectActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    val sub:BehaviorSubject<String> = BehaviorSubject.createDefault("")

    fun async(){
        val subject = AsyncSubject.create<String>()
        subject.onNext("1")
        subject.onNext("2")
        subject.onNext("3")
        subject.onNext("4")



    }
}