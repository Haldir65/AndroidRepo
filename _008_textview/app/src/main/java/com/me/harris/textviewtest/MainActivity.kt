package com.me.harris.textviewtest

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.me.harris.textviewtest.clippath.CustomDrawingActivity
import com.me.harris.textviewtest.config.UpdatingConfigActivity
import com.me.harris.textviewtest.constraint.ConstraintActivity
import com.me.harris.textviewtest.coordinated.CoordinateActivity
import com.me.harris.textviewtest.edittextpassword.EditTextActivity
import com.me.harris.textviewtest.flowable.FlowableMainActivity
import com.me.harris.textviewtest.interfaces.ItemClickListener
import com.me.harris.textviewtest.mail.SendMailActivity
import com.me.harris.textviewtest.progressbarstuyle.ProgressBarStyleActivity
import com.me.harris.textviewtest.shadow_layer_list.ShadowLayerListActivity
import com.me.harris.textviewtest.strikeThrough.StrikeThroughActivity
import com.me.harris.textviewtest.ui.CustomAdapter
import com.me.harris.textviewtest.ui._001_retrofit.RetrofitSampleActivity
import com.me.harris.textviewtest.ui._002_bitmap.BitmapActivity
import com.me.harris.textviewtest.ui._003_transform.GlideTransformActivity
import com.me.harris.textviewtest.view_pager.PagerActivity
import kotlinx.android.synthetic.main.activity_recycler_view.*

class MainActivity :AppCompatActivity(), ItemClickListener {



    lateinit var mAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initAdapter()
    }

    private fun initAdapter() {
        val userList = mutableListOf<Triple<String,Int,Class<out Activity>>>(
               Triple("BitmapSample",1, BitmapActivity::class.java),
                Triple("Retrofit",2,RetrofitSampleActivity::class.java),
                Triple("RoundCorner",3,GlideTransformActivity::class.java),
                Triple("Constraint",4,ConstraintActivity::class.java),
                Triple("Flowable",5,FlowableMainActivity::class.java),
                Triple("ClipPath",6,CustomDrawingActivity::class.java),
                Triple("Coordinate",7,CoordinateActivity::class.java),
                Triple("ClipPath",8,CustomDrawingActivity::class.java),
                Triple("Locale",9,UpdatingConfigActivity::class.java),
                Triple("Mail",10,SendMailActivity::class.java),
                Triple("pager",11,PagerActivity::class.java),
                Triple("EditText",12,EditTextActivity::class.java),
                Triple("progressbar",13,ProgressBarStyleActivity::class.java),
                Triple("shadow",14,ShadowLayerListActivity::class.java),
                Triple("Strike",15,StrikeThroughActivity::class.java)

        ).apply {
//            this.addAll(this)
//            this.addAll(this)
//            this.addAll(this)
//            this.addAll(this)
//            this.addAll(this)
        }

        mAdapter = CustomAdapter(userList,this)
        recyclerView1.adapter = mAdapter
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.addItemDecoration(object :  RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect?.set(12,5,12,5)
            }
        })
    }

    override fun onItemClick(position: Int, view: View) {
        val target = mAdapter.userList[position].third
        val intent = Intent(this,target)
        startActivity(intent)
//        Toast.makeText(this,"点击了 $position 以及View的id 是 ${view.id}",Toast.LENGTH_SHORT).show()
    }

}