package com.me.harris.ffmpegintegration

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_protocol -> tv_info.setText(urlprotocolinfo())
            R.id.btn_format -> tv_info.setText(avformatinfo())
            R.id.btn_codec -> tv_info.setText(avcodecinfo())
            R.id.btn_filter -> tv_info.setText(avfilterinfo())
            R.id.btn_player -> ffVideoPlayer.play("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
            else -> {
            }
        }
    }

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
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("xffmpeg")
        }
    }

    external fun urlprotocolinfo(): String

    external fun avformatinfo(): String

    external fun avcodecinfo(): String

    external fun avfilterinfo(): String
}
