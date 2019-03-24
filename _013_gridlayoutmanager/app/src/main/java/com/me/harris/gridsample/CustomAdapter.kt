package com.me.harris.gridsample


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class CustomAdapter(listCount: Int, FlowNum: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    constructor(listCount: Int, FlowNum: Int, desc: String) : this(listCount, FlowNum) {
        for (i in 0 until 100) {
            mData.add(ImageCluster())
        }
    }

    val mData = mutableListOf<ImageCluster>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_grid_sample_card -> {
                CustomImageFlowHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_grid_sample_card,
                        parent,
                        false
                    )
                )
            }
            R.layout.item_grid_sample_card_2 -> {
                CustomImageFlowHolder2(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_grid_sample_card_2,
                        parent,
                        false
                    )
                )

            }

            R.layout.item_grid_card_ll -> {
                CustomLayoutHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grid_card_ll,parent,false))
            }
            else -> {
                FullSpanHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_full_span,
                        parent,
                        false
                    )
                )

            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position < 5) {
            R.layout.item_full_span
        } else {
            R.layout.item_grid_card_ll
//            if (position % 2 == 0) {
//                R.layout.item_grid_card_ll
//            } else {
//                R.layout.item_grid_sample_card_2
//            }
        }
    }

    override fun getItemCount(): Int = mData.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CustomImageFlowHolder -> {
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = false
                holder.doBindData(mData[position])
            }
            is CustomImageFlowHolder2 -> {
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = false
                holder.doBindData(mData[position])
            }
            is FullSpanHolder ->{
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
                holder.doBindData(mData[position])
            }
            is CustomLayoutHolder -> {
               when(position%3){
                   0 -> {
                       holder.itemView.layoutParams.height = 450
                   }
                   1 -> {
                       holder.itemView.layoutParams.height = 560
                   }
                   else ->{
                       holder.itemView.layoutParams.height = 602
                   }
               }
            }
        }
    }
}

class CustomImageFlowHolder(v: View) : RecyclerView.ViewHolder(v) {

    var imag_center: ImageView

    init {
        imag_center = itemView.findViewById(R.id.imag_center)
    }

    fun doBindData(data: ImageCluster) {
        imag_center.setImageResource(data.resID)
    }
}

class CustomImageFlowHolder2(v: View) : RecyclerView.ViewHolder(v) {

    var imag_center: ImageView

    init {
        imag_center = itemView.findViewById(R.id.imag_center)
    }

    fun doBindData(data: ImageCluster) {
        imag_center.setImageResource(R.mipmap.img_11)
    }
}

class FullSpanHolder(v: View) : RecyclerView.ViewHolder(v) {

    var iv_center: ImageView

    init {
        iv_center = itemView.findViewById(R.id.iv_center)
    }

    fun doBindData(data: ImageCluster) {
//        iv_center.setImageResource(data.resID)
    }
}

class CustomLayoutHolder(view:View):RecyclerView.ViewHolder(view){

}

data class ImageCluster(val title: String = "主标题", val subtitle: String = "副标题", val resID: Int = R.mipmap.img_008)

