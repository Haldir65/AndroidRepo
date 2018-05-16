package com.me.harris.scrolllayoutsample;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> mDatas;

    @DrawableRes
    int picRes;

    public ListAapter() {
        mDatas = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mDatas.add("第"+String.valueOf(i) + "个数字");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_simple_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SimpleHolder) holder).bindData(mDatas.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

     class SimpleHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;
        public SimpleHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text1);
            mImageView = itemView.findViewById(R.id.image);
        }

        public void bindData(String s) {
            mTextView.setText(s);
            mImageView.setImageResource(picRes);
        }
    }

}
