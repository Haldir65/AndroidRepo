package com.me.harris.textviewtest.dsl

import okhttp3.*
import java.io.IOException

class RequestFields {
    lateinit var url: String
    internal var headers: MutableMap<String, String> = mutableMapOf()
    internal var body: RequestBodyFields? = null
}

class RequestBodyFields {
    var contentType: MediaType? = null
    lateinit var content: String
}

class EnqueueFields {
    lateinit var request: Request
    lateinit var onResponse: (Call, Response) -> Unit
    lateinit var onFailure: (Call, IOException) -> Unit
}

class ExecuteFields {
    lateinit var request: Request
}

fun get(init: RequestFields.() -> Unit): Request {
    val requestFields = RequestFields().apply(init)
    return Request.Builder().get()
            .headers(Headers.of(requestFields.headers))
            .url(requestFields.url)
            .build()
}

fun post(init: RequestFields.() -> Unit): Request {
    val requestFields = RequestFields().apply(init)
    return Request.Builder()
            .post(RequestBody.create(requestFields.body!!.contentType, requestFields.body!!.content))
            .headers(Headers.of(requestFields.headers))
            .url(requestFields.url)
            .build()
}

fun body(init: RequestBodyFields.() -> Unit): RequestBodyFields {
    return RequestBodyFields().apply(init)
}

fun OkHttpClient.enqueue(init: EnqueueFields.() -> Unit) {
    val enqueueFields = EnqueueFields().apply(init)
    this.newCall(enqueueFields.request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            enqueueFields.onResponse(call, response)
        }

        override fun onFailure(call: Call, e: IOException) {
            enqueueFields.onFailure(call, e)
        }
    })
}

@Throws(IOException::class)
fun OkHttpClient.execute(init: ExecuteFields.() -> Unit): Response {
    val executeFields = ExecuteFields().apply(init)
    return this.newCall(executeFields.request).execute()
}