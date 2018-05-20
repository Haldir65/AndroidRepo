package com.me.harris.textviewtest

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.me.harris.textviewtest.interfaces.ItemClickListener

class MainAdapter(val items:List<Triple<String,Int,Class<out Activity>>>,
                  val mListener:ItemClickListener) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CardItemHolder(v: View) :RecyclerView.ViewHolder(v){

    }
}