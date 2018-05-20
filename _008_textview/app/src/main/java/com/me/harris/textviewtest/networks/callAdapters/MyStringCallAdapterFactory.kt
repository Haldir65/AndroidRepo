package com.me.harris.textviewtest.networks.callAdapters

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class MyStringCallAdapterFactory : Converter.Factory() {

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return super.responseBodyConverter(type, annotations, retrofit)
    }


    class PrimitiveTypeRequestBodyConverter<F> :Converter<F,RequestBody>{
        override fun convert(value: F): RequestBody {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


    class PrimitiveTypeResponseBodyConverter<T>:Converter<ResponseBody,T>{
        
        override fun convert(value: ResponseBody?): T {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}