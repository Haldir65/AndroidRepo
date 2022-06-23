package com.me.harris.textviewtest.flowable

import android.nfc.Tag
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.me.harris.textviewtest.R
import com.me.harris.textviewtest.utils.LogUtil
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

class FlowableMainActivity :AppCompatActivity() {

    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button

    val compositions:CompositeDisposable = CompositeDisposable()

    companion object {
       val TAG = "FlowableMainActivity"
    }

    var source: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowable_main)

        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)


        btn1.setOnClickListener { _ ->
           Flowable.create<Int>({emitter ->
               for (i in 0..999) {
                   emitter.onNext(i)
               }
               emitter.onComplete()
           },BackpressureStrategy.BUFFER).onBackpressureBuffer(512,true,true,{ //这里unbounded为true的话，底层生成的queue的大小就是类似于无限的，不会出现Buffer is full 的问题，否则,queue的大小就是
               LogUtil.e(TAG,"overflow happened!! ")
           }).subscribeOn(Schedulers.io())
                   .subscribeOn(AndroidSchedulers.mainThread())
                   .subscribe(object :Subscriber<Int>{


                       override fun onComplete() {
                           source = null
                           LogUtil.e(TAG,"onComplete called !")

                       }

                       override fun onSubscribe(s: Subscription?) {
                           source = s
                           LogUtil.e(TAG,"ON Subscribe ")
                       }

                       override fun onNext(t: Int?) {
                           LogUtil.i(TAG,"ON NEXT __________ $t")

                       }

                       override fun onError(t: Throwable?) {
                           LogUtil.e(t?.message)
                           LogUtil.e(TAG,"onError called! ")
                       }

                   })
        }

        btn2.setOnClickListener {
            source?.request(200)
            LogUtil.e(TAG,"REQUESTING 200 MORE ELEMENTS====")
        }

        var sp:Subscription?=null


        btn3.setOnClickListener{
//            Flowable.fromPublisher<String> {    downstream ->
////                for(i in 1..1000){
////                  downstream.onNext(i.toString())
////                    LogUtil.i(TAG,"SEND DOWN MSG TO $i")
////                 }
////                downstream.onComplete()
//            }.
                    Flowable.fromPublisher<String>(object :Publisher<String>{

                        override fun subscribe(s: Subscriber<in String>?) {
                            for(i in 1..1000){
                                s?.onNext(i.toString())
                                LogUtil.e(TAG,"SENDING DOWN MSG $i")
                            }
                            s?.onComplete()
                        }
                    }).
           subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//                    .buffer(100,TimeUnit.SECONDS)
//                    .onBackpressureBuffer(1250,true,true)
                            .onBackpressureBuffer(10000)
            .subscribe(object : Subscriber<String> {
                override fun onComplete() {
                        LogUtil.e(TAG,"onComplete Called! ")
                }

                override fun onSubscribe(s: Subscription?) {
                    sp = s
                    sp?.request(1)
                    LogUtil.e(TAG,"REQUESTING INITIAL DATA")
                }

//                override fun onNext(t: MutableList<String>?) {
//                    if (t != null) {
//                        for (i in t.indices){
//                            LogUtil.e(TAG,"receive msg from upstream $i")
//
//                        }
//                    }
////                    LogUtil.e(TAG,"receive msg from upstream $t")
//                    sp?.request(1)
//                }

                override fun onError(t: Throwable?) {
                    LogUtil.e(TAG,t?.message)
                }

//                override fun onComplete() {
//                    LogUtil.e(TAG,"onComplete Called! ")
//                }
//
//                override fun onSubscribe(s: Subscription?) {
//                    sp = s
//                    sp?.request(1)
//                    LogUtil.e(TAG,"REQUESTING INITIAL DATA")
//                }
//
                override fun onNext(t: String?) {
                    LogUtil.e(TAG,"receive msg from upstream $t")
                    sp?.request(1)

                }
//
//                override fun onError(t: Throwable?) {
//                    LogUtil.e(TAG, t?.message)
//                }
            }
            )
        }
        // todo get weird error ,maybe publisher don't support backPressure?
        // Queue is full?!



//      应该是在 BackpressureBufferSubscriber.java这个类中  if (unbounded) {
//            q = SpscLinkedArrayQueue<T>(bufferSize)
//        } else {
//            q = SpscArrayQueue<T>(bufferSize)
//        }

        // this is functional
        var sp4:Subscription?=null
        btn4.setOnClickListener {

          Flowable.interval(1,TimeUnit.SECONDS)
                  .onBackpressureBuffer(100)
                  .observeOn(Schedulers.newThread())
                  .subscribe(
                          {
                             LogUtil.e(TAG,"GET MESSAGE $it")
                              sp4?.request(1)
                          },{
                      LogUtil.e(TAG,it.message)
                        },{
                            LogUtil.e(TAG,"onCompleted called !")
                            },{
                            sp4 = it
                        it.request(1)
                        }
                  )
        }

        var sp5:Subscription?= null

        btn5.setOnClickListener {
            Flowable.create<String>({emitter ->
                for(i in 0..1000){
                    emitter.onNext(i.toString())
                    LogUtil.e(TAG,"send down message $i")
                }
                emitter.onComplete()
            },BackpressureStrategy.BUFFER)
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :FlowableSubscriber<String>{
                        override fun onComplete() {
                            LogUtil.e(TAG,"OnComplete called ")
                        }

                        override fun onSubscribe(s: Subscription) {
                            sp5 = s
                            sp5?.request(1)
                        }

                        override fun onNext(t: String?) {
                            LogUtil.e(TAG,"receive message $t")
                            Thread.sleep(100)
                            sp5?.request(1)

                        }

                        override fun onError(t: Throwable?) {
                            LogUtil.e(TAG,t?.message)
                        }

                    })
        }

//        Flowable.create<Int>({emitter ->
//            for (i in 0..999) {
//                emitter.onNext(i)
//            }
//            emitter.onComplete()
//        },BackpressureStrategy.BUFFER)

    }
}