package com.me.harris.ffmpegintegration

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("xffmpeg")
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_protocol -> tv_info.setText(urlprotocolinfo())
            R.id.btn_format -> tv_info.setText(avformatinfo())
            R.id.btn_codec -> tv_info.setText(avcodecinfo())
            R.id.btn_filter -> tv_info.setText(avfilterinfo())
            R.id.btn_player -> {
                val url = if (!TextUtils.isEmpty(localMp4Files.getOrNull(0)))
                    localMp4Files.getOrNull(0)
                else "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
                ffVideoPlayer.play(url)
            }
            R.id.btn_stop_play -> stopPlay()
            else -> {
            }
        }
    }
    val localMp4Files = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
//        sample_text.text = stringFromJNI()
        btn_protocol.setOnClickListener(this)
        btn_codec.setOnClickListener(this)
        btn_filter.setOnClickListener(this)
        btn_format.setOnClickListener(this)
        btn_player.setOnClickListener(this)
        btn_stop_play.setOnClickListener(this)

        thread {
            if(ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val list = VideoUtil.getVideoFileAbsolutePathList()
                list?.forEach {
                    localMp4Files.add(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
            arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),1098
            )
        }
    }



    external fun urlprotocolinfo(): String

    external fun avformatinfo(): String

    external fun avcodecinfo(): String

    external fun avfilterinfo(): String

    external fun stopPlay():String
}
