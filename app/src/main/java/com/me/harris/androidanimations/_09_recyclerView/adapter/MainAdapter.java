package com.me.harris.androidanimations._09_recyclerView.adapter;

import androidx.annotation.LayoutRes;
import androidx.core.util.Pair;

import com.me.harris.androidanimations._09_recyclerView.viewholer.DataBoundViewHolder;
import com.me.harris.androidanimations.databinding.ItemMainBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;
import com.me.harris.androidanimations.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermi on 2016/9/21.
 */

public class MainAdapter<T> extends DataBoundAdapter<ItemMainBinding> {

    private List<Pair<String, Class>> mDatas;

    private GenericCallBack<T> actionCallBack;

    /**
     * Creates a DataBoundAdapter with the given item layout
     *
     * @param layoutId The layout to be used for items. It must use data binding.
     */
    public MainAdapter(@LayoutRes int layoutId, GenericCallBack<T> callBack) {
        super(layoutId);
        this.actionCallBack = callBack;
        /*mDatas = new ArrayList<>();
        mDatas.add(new Pair<String,Class>("ViewAnimation", ViewAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("DrawableAnimation", DrawableAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("PropertyAnimation", PropertyAnimationActivity.class));
        mDatas.add(new Pair<String,Class>("BouncingBalls", BouncingBalls.class));
        mDatas.add(new Pair<String,Class>("ShadowCard", ShadowCardDrag.class));
        mDatas.add(new Pair<String,Class>("CardImageView", MaterialWitness.class));
        mDatas.add(new Pair<String,Class>("Canvas", CanvasActivity.class));*/
      /* actionCallBack = new GenericCallBack<Pair<String,Class>>() {
           Intent intent = new Intent();

           @Override
           public void onClick(View view, Pair<String,Class> pair) {
               intent.setClass((view.getContext()), pair.second);
               view.getContext().startActivity(intent);
           }
       };*/

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

    public void setDatas(List<Pair<String, Class>> mDatas) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList<>();
        }
        this.mDatas.addAll(mDatas);
    }

    public void setActionCallBack(GenericCallBack<T> actionCallBack) {
        this.actionCallBack = actionCallBack;
    }
}
