package com.me.harris.textviewtest.ui._001_retrofit

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.me.harris.textviewtest.R
import com.me.harris.textviewtest.model.MainPage
import com.me.harris.textviewtest.networks.NetWorkManager
import com.me.harris.textviewtest.networks.api.QiNiuApi
import com.me.harris.textviewtest.networks.digest.BaseNeteorkObserver
import com.me.harris.textviewtest.schedule.RxCompose
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitSampleActivity :AppCompatActivity() {


    lateinit var mTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          mTextView = TextView(this)
        mTextView.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            gravity =Gravity.CENTER
            leftMargin = 20
            rightMargin = 20
        }
        mTextView.setTextColor(ContextCompat.getColor(this, R.color.md_green_800))
        mTextView.setText("哈哈哈",TextView.BufferType.NORMAL)
        setContentView(mTextView)
//        callService()
//        getObservableData()
//        getObservable3()
        getPlainText()
    }

    fun callService(){
      val api = NetWorkManager.createRetrofitApi(QiNiuApi::class.java)
        api.mainPage(10,1)
                .enqueue(object  : Callback<MainPage> {
                    override fun onFailure(call: Call<MainPage>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<MainPage>?, response: Response<MainPage>?) {
                        if (response != null) {
                            val data:MainPage = response.body() as MainPage
                            mTextView.text = data.results[0].desc
                        }
                    }

                })
    }

    fun getObservableData(){
        NetWorkManager.createRetrfitApiObservable(QiNiuApi::class.java).mainPage2(10,1)
                .compose{
                    upstream ->
                    upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
                .subscribeWith(object : DisposableObserver<MainPage>(){
                    override fun onComplete() {
                        mTextView.text = mTextView.text.toString()+"completed"

                    }

                    override fun onNext(t: MainPage) {
                        mTextView.text = t.results[0].desc
                    }

                    override fun onError(e: Throwable) {
                        mTextView.text = e.message
                    }

                })

    }

    fun getObservable3(){
        NetWorkManager.createRetrfitApiObservable(QiNiuApi::class.java).mainPage2(10,1)
                .compose(RxCompose.io_mian())
                .subscribeWith(object : BaseNeteorkObserver<MainPage>() {
                    override fun onNext(t: MainPage) {
                        super.onNext(t)
                        mTextView.text = t.results[0].desc
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }

    fun getPlainText(){
        Observable.fromCallable<String> {
            NetWorkManager.createRetrfitApiObservable(QiNiuApi::class.java)
                    .mainPageAsString(10,1)
        }.compose{upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
                .subscribe{data->
                    mTextView.text = data

                }


    }




}