package com.me.harris.textviewtest.networks.api

import com.me.harris.textviewtest.model.MainPage
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QiNiuApi {

    @GET("data/Android/{page}/{limit}")
    fun mainPage(@Path("page")page:Int,@Path("limit") limit:Int):Call<MainPage>

    @GET("data/Android/{page}/{limit}")
    fun mainPage2(@Path("page")page:Int,@Path("limit") limit:Int):Observable<MainPage>

    @GET("data/Android/{page}/{limit}")
    fun mainPageAsString(@Path("page")page:Int,@Path("limit") limit:Int):String

}