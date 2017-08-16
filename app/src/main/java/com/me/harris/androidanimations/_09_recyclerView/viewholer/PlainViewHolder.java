package com.me.harris.androidanimations._09_recyclerView.viewholer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.interfaces.OnItemClickListener;

/**
 * Created by Harris on 2017/2/12.
 */

public class PlainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  public   TextView textView;

    OnItemClickListener mListener;

    public PlainViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
    }

    public PlainViewHolder(View itemView, OnItemClickListener mListener) {
        this(itemView);
        textView.setOnClickListener(this);
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClicked(v, getAdapterPosition());
        }
    }
}
