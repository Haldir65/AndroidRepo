package com.me.harris.droidx.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.me.harris.droidx.R
import com.me.harris.droidx.inflate

class CustomAdapter(val userList:List<Triple<String,Int,Class<out Activity>>>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    lateinit var listener:ItemClickListener

    constructor(userList: List<Triple<String,Int,Class<out Activity>>>, listener:ItemClickListener):this(userList){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_layout)).apply {
            itemView.setOnClickListener { view ->
                listener.onItemClick(adapterPosition,view)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName?.text = userList[position].first
        holder.txtTitle?.text = userList[position].second.toString()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)

    }
}