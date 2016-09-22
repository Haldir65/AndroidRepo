package com.me.harris.androidanimations.ui.recyclerview.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.util.Pair;
import android.view.View;

import com.me.harris.androidanimations.databinding.ItemMainBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;
import com.me.harris.androidanimations.ui.recyclerview.viewholer.DataBoundViewHolder;
import com.me.harris.androidanimations.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermi on 2016/9/21.
 */

public class MainActivityAdapter extends DataBoundAdapter<ItemMainBinding> {

    private List<Pair<String, Class>> mDatas;

    private GenericCallBack<Pair<String,Class>> actionCallBack;
    /**
     * Creates a DataBoundAdapter with the given item layout
     *
     * @param layoutId The layout to be used for items. It must use data binding.
     */
    public MainActivityAdapter(@LayoutRes int layoutId) {
        super(layoutId);
        /*mDatas = new ArrayList<>();
        mDatas.add(new Pair<String,Class>("ViewAnimation", ViewAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("DrawableAnimation", DrawableAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("PropertyAnimation", PropertyAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("BouncingBalls", BouncingBalls.class));
        mDatas.add(new Pair<String,Class>("ShadowCard", ShadowCardDrag.class));
        mDatas.add(new Pair<String,Class>("CardImageView", MaterialWitness.class));
        mDatas.add(new Pair<String,Class>("Canvas", CanvasActivity.class));*/
       actionCallBack = new GenericCallBack<Pair<String,Class>>() {
           Intent intent = new Intent();

           @Override
           public void onClick(View view, Pair<String,Class> pair) {
               intent.setClass((view.getContext()), pair.second);
               view.getContext().startActivity(intent);
           }
       };

    }

    @Override
    protected void bindItem(DataBoundViewHolder<ItemMainBinding> holder, int position, List<Object> payloads) {
        holder.binding.setData(mDatas.get(position));
        holder.binding.setCallback(actionCallBack);
    }



    @Override
    public int getItemCount() {
        return Utils.isListEmpty(mDatas) ? 0 : mDatas.size();
    }

    public void setmDatas(List<Pair<String, Class>> mDatas) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.addAll(mDatas);
    }

    public void setActionCallBack(GenericCallBack<Pair<String, Class>> actionCallBack) {
        this.actionCallBack = actionCallBack;
    }
}
