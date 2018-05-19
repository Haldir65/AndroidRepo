package com.me.harris.textviewtest

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.me.harris.textviewtest.utils.screenHeight
import com.me.harris.textviewtest.utils.screenWitdth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_PATH = ""
        const val MAX_BITMAP_SIZE = 100 * 1024 * 1024; // 100 MB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        Glide.with(this)
                .asBitmap()
                .load(IMAGE_PATH)
                .into(object : SimpleTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N&&resource.allocationByteCount>MAX_BITMAP_SIZE) {
                            val screenWidth = screenWitdth()
                            val screenHeight = screenHeight()
                            val scaled:Bitmap = Bitmap.createScaledBitmap(resource,screenWidth,resource.height*screenHeight/resource.width,false)
                            imageone.setImageBitmap(scaled)
                        } else {
                            imageone.setImageBitmap(resource)
                        }
//                        val screenWidth = screenWitdth()
//                        val screenHeight = screenHeight()
//                        val scaled:Bitmap = Bitmap.createScaledBitmap(resource,screenWidth,resource.height*screenHeight/(resource.width),false)
//                        val scaled2:Bitmap = Bitmap.createScaledBitmap(resource,screenWidth,resource.height*screenHeight/(resource.width),true) //
                        // https://stackoverflow.com/questions/2895065/what-does-the-filter-parameter-to-createscaledbitmap-do
                        //第四个参数传true会在downSampling的时候取样更加smooth，效果
                        // Passing filter = false will result in a blocky, pixellated image.
//                        Passing filter = true will give you smoother edges.

//                        imageone.setImageBitmap(scaled)

//

                    }
                })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
