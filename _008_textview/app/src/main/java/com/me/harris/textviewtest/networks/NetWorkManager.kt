package com.me.harris.textviewtest.networks

import com.me.harris.textviewtest.paser.MoshiHolder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class NetWorkManager {


    companion object {
        const val BASE_URL = "http://gank.io/api/"


        fun <T> createRetrofitApi( cal:Class<T>):T{
            val retrofit = Retrofit.Builder()
                    .baseUrl(NetWorkManager.BASE_URL)
                    .client(OkHttpClientProvider.INSTANCE.client())
                    .addConverterFactory(MoshiConverterFactory.create(MoshiHolder.instance.moshi))
                    .build()
            return retrofit.create(cal)
        }

      fun <T> createRetrfitApiObservable(cla:Class<T>):T {
          val retrofit = Retrofit.Builder()
                  .baseUrl(NetWorkManager.BASE_URL)
                  .client(OkHttpClientProvider.INSTANCE.client())
                  .addConverterFactory(MoshiConverterFactory.create(MoshiHolder.instance.moshi))
                  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                  .build()
          return retrofit.create(cla)
      }

    }




}