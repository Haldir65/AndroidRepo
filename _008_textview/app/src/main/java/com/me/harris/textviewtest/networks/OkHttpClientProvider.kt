package com.me.harris.textviewtest.networks

import okhttp3.OkHttpClient


// object关键字是单例的简单实现，其实是一个static代码块初始化了Instance
 class OkHttpClientProvider private constructor(){


    val logicClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
                .build()
    }

    companion object {
        val INSTANCE:OkHttpClientProvider by lazy {
            OkHttpClientProvider()
        }
    }

    fun client():OkHttpClient = logicClient



}