package com.me.harris.androidanimations._37_horizontal_scroll

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.me.harris.androidanimations.R


/**
 * Created by Harris on 2017/6/12.
 */
class PickerAdapter (private val context: Context, private val recyclerView: RecyclerView?): RecyclerView.Adapter<PickerAdapter.TextVH>() {

    private var dataList: List<String>? = null

    fun PickerAdapter(context: Context, dataList: List<String>, recyclerView: RecyclerView): Unit {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.picker_item_layout, parent, false)
        return TextVH(view)
    }

    override fun onBindViewHolder(holder: TextVH, position: Int) {
        val textVH = holder
        textVH.pickerTxt.text = dataList!![position]
        textVH.pickerTxt.setOnClickListener {
            recyclerView?.smoothScrollToPosition(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    fun swapData(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged()
    }




    inner class TextVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pickerTxt: TextView = itemView.findViewById<TextView>(R.id.picker_item)

    }
}