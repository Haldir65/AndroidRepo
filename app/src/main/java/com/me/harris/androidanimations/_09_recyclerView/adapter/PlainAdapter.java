package com.me.harris.androidanimations._09_recyclerView.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._09_recyclerView.viewholer.PlainViewHolder;

import java.util.List;

/**
 * Created by Harris on 2017/2/12.
 */

public class PlainAdapter extends RecyclerView.Adapter<PlainViewHolder>{
    public void setItems(List<Person> list) {
        // TODO: 2017/2/5 更新列表只需调用set函数，后续UI更新由DiffUtil完成
    }

    @Override
    public PlainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plain_card_view, parent, false);
        return new PlainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlainViewHolder holder, int position) {
//            holder.textView.setText("Merry Christmas  " + position);
    }

    @Override
    public int getItemCount() {
        return 1000;
    }


    private static class Person{
        int id;
    }
}
