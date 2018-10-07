package com.me.harris.textviewtest.view_pager

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.me.harris.textviewtest.MainActivity
import com.me.harris.textviewtest.R

class PagerFragment:Fragment() {

    companion object {
        fun newInstance(src:Int,container:String):PagerFragment{
            return PagerFragment().apply {
                arguments = Bundle().apply { putInt("img_src",src)
                    putString("contain",container)
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val image = ImageView(container!!.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600)
            setImageResource(arguments?.getInt("img_src")?:R.drawable.img_20)
        }
//        image.setOnClickListener {
//            it.apply {
//                context.startActivity(Intent(context,MainActivity::class.java))
//            }
//        }
        return image
    }

    fun supplyItemPostion():Int{
        return arguments?.getInt("img_src")?:R.drawable.img_20
    }

    override fun toString(): String {
        return "${super.toString()} from ${arguments!!.getString("contain")}"
    }

}