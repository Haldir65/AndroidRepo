package com.me.harris.droidx

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.TextureView
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.me.harris.droidx.adapter.CustomAdapter
import com.me.harris.droidx.adapter.ItemClickListener
import com.me.harris.droidx.cronet.CronetActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycler_view.*

class MainActivity : AppCompatActivity(), ItemClickListener {


    lateinit var mAdapter: CustomAdapter

    class GenericThing<T>(constructorArg: T) {
        // 1. Define a member property
        private val thing: T = constructorArg

        // 2. Define the type of a function argument
        fun doSomething(thing: T) = println(thing)

        // 3. Use as a type argument
        fun emptyList() = listOf<T>()

        // 4. Use as a type parameter constraint, and...
        // 5. Cast to the type (produces "unchecked cast" warning)
        fun <U : T> castIt(): T = thing as U
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initAdapter()
        showDlg()

    }

    fun showDlg(){
        val d = Dialog(this as Context)
            .apply {
                setTitle("title")
                setCancelable(true)
                setCanceledOnTouchOutside(true)
            }.show()

    }


    fun initAdapter(){
        val userList = mutableListOf<Triple<String,Int,Class<out Activity>>>(
            Triple("Cronet",1, CronetActivity::class.java)
//            Triple("Retrofit",2,RetrofitSampleActivity::class.java),
//            Triple("RoundCorner",3,GlideTransformActivity::class.java),
//            Triple("Constraint",4,ConstraintActivity::class.java),
//            Triple("Flowable",5,FlowableMainActivity::class.java),
//            Triple("ClipPath",6,CustomDrawingActivity::class.java),
//            Triple("Coordinate",7,CoordinateActivity::class.java),
//            Triple("ClipPath",8,CustomDrawingActivity::class.java),
//            Triple("Locale",9,UpdatingConfigActivity::class.java),
//            Triple("Mail",10,SendMailActivity::class.java),
//            Triple("pager",11,PagerActivity::class.java),
//            Triple("EditText",12,EditTextActivity::class.java),
//            Triple("progressbar",13,ProgressBarStyleActivity::class.java),
//            Triple("shadow",14,ShadowLayerListActivity::class.java),
//            Triple("Strike",15,StrikeThroughActivity::class.java)
        )
        mAdapter = CustomAdapter(userList,this)
        recyclerView1.adapter = mAdapter
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.addItemDecoration(object :  RecyclerView.ItemDecoration(){

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.set(12,5,12,5)
            }
        })
    }

    override fun onItemClick(position: Int, view: View) {
        val target = mAdapter.userList[position].third
        val intent = Intent(this,target)
        startActivity(intent)
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
