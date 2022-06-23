package com.me.harris.textviewtest.dsl

import okhttp3.OkHttpClient
// https://medium.com/datadriveninvestor/write-your-android-networking-as-a-kotlin-dsl

fun use(client:OkHttpClient){
    val response = client.execute {
        request = get {
            url = "https://www.google.com"
        }
    }

    // Asynchronous example
    client.enqueue {
        request = post {
            url = "https://www.google.com"
            headers = mutableMapOf("headerName" to "headerValue")
            body {
                content = "{}"
            }
        }

        onFailure = { call, ioException ->

        }

        onResponse = { call, response ->

        }
    }
}