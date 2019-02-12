package com.me.harris.jniscratch

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_playground.*
import java.io.File

class PlayGroundActivity : AppCompatActivity() {
    companion object {
        val TAG = "PlayGroundActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)
        initData()
    }

    fun initData() {
        textView1.setOnClickListener {
            val crypto = Crypto()

            val crypted = crypto.encrypt("textView1")
            textView1.text = crypted

            val decoded = crypto.decrypt(crypted)
            textView2.text = decoded
        }

        textView2.setOnClickListener {
            val keyFromjni = PackageValidate.getPublicKey(this@PlayGroundActivity)
            textView2.text = keyFromjni
            Log.e(TAG, keyFromjni)
            Log.e(TAG, textView2.text.toString())
        }

        textView3.setOnClickListener {
            val signature = Validator.getSignature(this)
            textView3.text = (signature!!.substring(10, 20))
        }

        textView4.setOnClickListener {
//            val abs = "/sdcard/hello.txt"
            val abs = Environment.getExternalStorageDirectory().absolutePath+File.separator+Environment.DIRECTORY_DOWNLOADS+File.separator+"hellothere.txt"
//            Log.e(TAG, abs)
            val result = CreatingFileUsingJni().createFileUsingJni(abs, "we think this is indeed awesome\n")
//            Log.e(TAG, result)
        }


    }


}