package com.me.harris.textviewtest.jsonfast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.me.harris.textviewtest.R
import com.me.harris.textviewtest.utils.LogUtil

class FastJsonDemoActivity :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_serialize)
        val original = "{\"args\":{},\"goto_external_browser\":[\"iOS\",\"Android\"],\"type\":\"const\",\"value\":\"http://share.telebox88.com/XiaocongGroup\"}"
        val obj = JSON.parseObject(original,ConfigObject::class.java)
        LogUtil.e("==",obj.toString())
    }


}