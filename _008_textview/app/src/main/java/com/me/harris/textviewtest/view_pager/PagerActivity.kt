package com.me.harris.textviewtest.view_pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.me.harris.textviewtest.R
import kotlinx.android.synthetic.main.activity_simple_pager.*
import java.util.*

class PagerActivity:AppCompatActivity(){




    companion object {
        val datas = arrayOf(R.drawable.img_1,
                R.drawable.img_01,
                R.drawable.img_008,
                R.drawable.img_007,
                R.drawable.img_20,
                R.drawable.img_10,
                R.drawable.img_009)
        val TAG = "==HEADS="
    }

    lateinit var adapter1:MyPagerAdapter
    lateinit var adapter2:MyStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState==null)
        {
            Log.e(TAG,"onCreate")
        }else{
            Log.e(TAG,"onCreate not empty====================")
        }

        setContentView(R.layout.activity_simple_pager)
        adapter1 = MyPagerAdapter(supportFragmentManager)
                .apply {
                    repeat(datas.size) {
                        myFragments.add(PagerFragment.newInstance(datas[it],"pager"))
                    }
                }
        pager1.adapter = adapter1

        adapter2 = MyStatePagerAdapter(supportFragmentManager).apply {
            repeat(datas.size) {
                myFragments.add(PagerFragment.newInstance(datas[it],"statePager"))
            }
        }
        pager2.adapter = adapter2

        tv_1.setOnClickListener {
            val list = adapter1.myFragments
            Collections.swap(list,0,1)
            adapter1.notifyDataSetChanged()
            pager1.setCurrentItem(0,false)
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.e(TAG,"onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(TAG,"onRestoreInstanceState")
    }


    class MyPagerAdapter(manager:FragmentManager):FragmentPagerAdapter(manager){

        val myFragments = mutableListOf<Fragment>()

        override fun getItem(position: Int): Fragment {
            return myFragments[position]
        }

        override fun getCount(): Int {
            return myFragments.size
        }


        override fun getItemId(position: Int): Long {
            return (myFragments[position] as PagerFragment).hashCode().toLong()
        }

        override fun getItemPosition(`object`: Any): Int {
            //如果是两个数据之间调换了的话
            return myFragments.indexOf(`object`)
        }
    }

    class MyStatePagerAdapter(manager: FragmentManager):FragmentStatePagerAdapter(manager){
        val myFragments = mutableListOf<Fragment>()

        override fun getItem(position: Int): Fragment {
            return myFragments[position]
        }

        override fun getCount(): Int {
            return myFragments.size
        }

    }




}