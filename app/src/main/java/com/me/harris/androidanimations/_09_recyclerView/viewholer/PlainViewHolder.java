package com.me.harris.androidanimations._09_recyclerView.viewholer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.me.harris.androidanimations.R;

/**
 * Created by Harris on 2017/2/12.
 */

public class PlainViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public PlainViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
    }
}
