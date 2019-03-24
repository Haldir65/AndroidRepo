package com.me.harris.gridsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_grid_sample.*


class GridSampleActivity: AppCompatActivity() {

    val adapter_ by lazy {
        CustomAdapter(10,10,"john")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_sample)
        recycler_flow?.run {
            adapter = adapter_
            layoutManager = CustomStaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            addItemDecoration(SimpleDecoration())
            addOnScrollListener(object :RecyclerView.OnScrollListener(){

                val temp = intArrayOf(-1,-1)

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = layoutManager as StaggeredGridLayoutManager
                    manager.findFirstVisibleItemPositions(temp)
                    val position =  Math.min(temp[0],temp[1])
                    if (position>=5){
                        fake_strip.visibility = View.VISIBLE
                    }else{
                        fake_strip.visibility = View.GONE
                    }
                    manager.findLastVisibleItemPositions(temp)
                    val position2 = Math.max(temp[0],temp[1])
                    if (position2+4>recyclerView.adapter!!.itemCount){
                        Log.w("==","STARTING PRELOADING")
                    }
                }
            })
        }
    }

}