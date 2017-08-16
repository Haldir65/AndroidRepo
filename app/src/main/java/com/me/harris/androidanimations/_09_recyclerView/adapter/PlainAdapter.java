package com.me.harris.androidanimations._09_recyclerView.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._09_recyclerView.viewholer.PlainViewHolder;
import com.me.harris.androidanimations.interfaces.OnItemClickListener;

import java.util.List;

/**
 * Created by Harris on 2017/2/12.
 */

public class PlainAdapter extends RecyclerView.Adapter<PlainViewHolder>{

    List<Person> mList;
    OnItemClickListener mListener;

    public void setItems(List<Person> list) {
        // TODO: 2017/2/5 更新列表只需调用set函数，后续UI更新由DiffUtil完成
        mList = list;
    }

    public PlainAdapter(List<Person> mList, OnItemClickListener mListener) {
        this.mList = mList;
        this.mListener = mListener;
    }

    public PlainAdapter(List<Person> mList) {
        this.mList = mList;
    }

    public PlainAdapter() {
    }

    @Override
    public PlainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plain_card_view, parent, false);
        return new PlainViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(PlainViewHolder holder, int position) {
        Person data = mList.get(position);
        holder.textView.setText(data.text);
        if (data.textColor != 0) {
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), data.textColor));
        }
    }

    @Override
    public int getItemCount() {
        return mList== null?0:mList.size();
    }



}
